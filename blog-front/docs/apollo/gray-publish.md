---
slug: /apollo/gray-publish
title: Config - 灰度配置发布
date: 2026-01-15
---

## 简介

分析灰度配置更改、下发、作废或合并的流程。

## 流程说明

灰度配置发布通过独立的接口触发，灰度规则通过 `GrayReleaseRulesHolder` 维护，并通过 `ReleaseMessage` 监听消息触发规则更新。

## 核心代码

```java
public class GrayReleaseRulesHolder implements ReleaseMessageListener, InitializingBean {
  private static final Logger logger = LoggerFactory.getLogger(GrayReleaseRulesHolder.class);
  private static final Joiner STRING_JOINER = Joiner.on(ConfigConsts.CLUSTER_NAMESPACE_SEPARATOR);

  private final GrayReleaseRuleRepository grayReleaseRuleRepository;
  private final BizConfig bizConfig;

  private int databaseScanInterval;
  private ScheduledExecutorService executorService;
  //store configAppId+configCluster+configNamespace -> GrayReleaseRuleCache map
  private Multimap<String, GrayReleaseRuleCache> grayReleaseRuleCache;
  //store clientAppId+clientNamespace+ip -> ruleId map
  //基于client ip的灰度缓存map
  private Multimap<String, Long> reversedGrayReleaseRuleCache;
  //store clientAppId+clientNamespace+label -> ruleId map
  //client label的灰度map
  private Multimap<String, Long> reversedGrayReleaseRuleLabelCache;
  //an auto increment version to indicate the age of rules
  private AtomicLong loadVersion;

  @Override
  public void afterPropertiesSet() throws Exception {
    //每分钟同步刷新一次灰度数据
    populateDataBaseInterval();
    //force sync load for the first time
    periodicScanRules();
    executorService.scheduleWithFixedDelay(this::periodicScanRules,
        getDatabaseScanIntervalSecond(), getDatabaseScanIntervalSecond(), getDatabaseScanTimeUnit()
    );
  }

  //查找namespace是否有灰度规则，合并到缓存
  @Override
  public void handleMessage(ReleaseMessage message, String channel) {
    logger.info("message received - channel: {}, message: {}", channel, message);
    String releaseMessage = message.getMessage();
    if (!Topics.APOLLO_RELEASE_TOPIC.equals(channel) || Strings.isNullOrEmpty(releaseMessage)) {
      return;
    }
    List<String> keys = ReleaseMessageKeyGenerator.messageToList(releaseMessage);
    //message should be appId+cluster+namespace
    if (CollectionUtils.isEmpty(keys)) {
      return;
    }
    String appId = keys.get(0);
    String cluster = keys.get(1);
    String namespace = keys.get(2);

    List<GrayReleaseRule> rules = grayReleaseRuleRepository
        .findByAppIdAndClusterNameAndNamespaceName(appId, cluster, namespace);

    mergeGrayReleaseRules(rules);
  }

  //每60s扫描
  private void periodicScanRules() {
    Transaction transaction = Tracer.newTransaction("Apollo.GrayReleaseRulesScanner",
        "scanGrayReleaseRules");
    try {
      loadVersion.incrementAndGet();
      scanGrayReleaseRules();
      transaction.setStatus(Transaction.SUCCESS);
    } catch (Throwable ex) {
      transaction.setStatus(ex);
      logger.error("Scan gray release rule failed", ex);
    } finally {
      transaction.complete();
    }
  }

  //AbstractConfigService在获取配置时会调用，查看是否有灰度Release，通过GrayReleaseRuleCache.matches方法判定clientIP或label是否满足条件
  public Long findReleaseIdFromGrayReleaseRule(String clientAppId, String clientIp, String clientLabel, String
      configAppId, String configCluster, String configNamespaceName) {
    String key = assembleGrayReleaseRuleKey(configAppId, configCluster, configNamespaceName);
    if (!grayReleaseRuleCache.containsKey(key)) {
      return null;
    }
    //create a new list to avoid ConcurrentModificationException
    List<GrayReleaseRuleCache> rules = Lists.newArrayList(grayReleaseRuleCache.get(key));
    for (GrayReleaseRuleCache rule : rules) {
      //check branch status
      if (rule.getBranchStatus() != NamespaceBranchStatus.ACTIVE) {
        continue;
      }
      if (rule.matches(clientAppId, clientIp, clientLabel)) {
        return rule.getReleaseId();
      }
    }
    return null;
  }

  /**
   * Check whether there are gray release rules for the clientAppId, clientIp, clientLabel, namespace combination.
   * Please note that even there are gray release rules, it doesn't mean it will always load gray
   * releases. Because gray release rules actually apply to one more dimension - cluster.
   */
  public boolean hasGrayReleaseRule(String clientAppId, String clientIp, String clientLabel,
      String namespaceName) {
    // check ip gray rule
    if (reversedGrayReleaseRuleCache.containsKey(assembleReversedGrayReleaseRuleKey(clientAppId,
        namespaceName, clientIp)) || reversedGrayReleaseRuleCache.containsKey
        (assembleReversedGrayReleaseRuleKey(clientAppId, namespaceName, GrayReleaseRuleItemDTO
            .ALL_IP))) {
      return true;
    }
    // check label gray rule
    if (!Strings.isNullOrEmpty(clientLabel) &&
        (reversedGrayReleaseRuleLabelCache.containsKey(
            assembleReversedGrayReleaseRuleKey(clientAppId, namespaceName, clientLabel)) ||
            reversedGrayReleaseRuleLabelCache.containsKey(
                assembleReversedGrayReleaseRuleKey(clientAppId, namespaceName,
                    GrayReleaseRuleItemDTO.ALL_Label)))) {
      return true;
    }
    return false;
  }

  //省略……

}
```

## 总结

灰度发布的核心是维护一份独立的 release，并通过 IP / label 规则做客户端分流；规则变更通过 `ReleaseMessage` 触发，本地定期扫描兜底，确保灰度配置与主分支发布的一致性。