---
slug: /apollo/spi-and-client-config
title: Apollo client - SPI 和 client 配置简析
date: 2026-01-15
---

# 简介

这一节我们看看Apollo client有哪些拓展能力，首先看Apollo提供了哪些SPI实现，然后分析Apollo client有哪些可更改的配置项。架构组在未来迭代中，可因应需求和业务代码情况，参考本文描述定制一些实现。

# Apollo client SPI

Apollo client采用Java service SPI的方式去加载SPI功能组件。

以下是一些默认实现，必要时参考下面定制公司内实现：

- DefaultMetaServerProvider：通过一定优先级获取meta server地址，一般来说企业搭建Apollo**建议统一覆盖**。
- Injector（DefaultInjector）：Apollo client的bean工厂，默认实现Guice，可重写为使用Spring框架。
- ConfigServiceLoadBalancerClient（RandomConfigServiceLoadBalancerClient）：configService的负载均衡实现，有多个config server时决定选择config server。默认随机负载。
- ApolloClientMessageProducerManager：Apollo client的监控事件派送器。
- ApolloConfigRegistrarHelper（DefaultApolloConfigRegistrarHelper）： @EnableApolloConfig启动配置解析用到的注册器。
- ConfigPropertySourcesProcessorHelper（DefaultConfigPropertySourcesProcessorHelper）：Spring Boot和XML启动用的Apollo配置注册器。



# ConfigUtil类配置分析

**官网没有完整列举client的**配置能力，这里做补充。

`ConfigUtil`是一个配置工具类，，这里说下ConfigUtil里的每项配置作用：

```java
/**
 * qps limit: discovery config service from meta
 * <p>
 * 1 times per second
 */
private int discoveryQPS = 1;  //apollo.discoveryQPS，请求meta service的限流QPS，默认1
/** 1 second */
private int discoveryConnectTimeout = 1000; //apollo.discoveryConnectTimeout，请求meta service的connect超时
/** 1 second */
private int discoveryReadTimeout = 1000; //apollo.discoveryReadTimeout，请求meta service的socket读超时

private int refreshInterval = 5; //apollo.refreshInterval，默认每5分钟拉取一次apollo配置
private TimeUnit refreshIntervalTimeUnit = TimeUnit.MINUTES;
private int connectTimeout = 1000; //1 second，apollo.connectTimeout
private int readTimeout = 5000; //5 seconds，apollo.readTimeout
private String cluster; //默认default，取值优先级system.property."apollo.cluster" -> spring boot env "apollo.cluster" -> system.property."idc" -> env "idc" -> server.properties "idc"
private int loadConfigQPS = 2; //apollo.loadConfigQPS, （单个app）限制总拉取qps
private int longPollQPS = 2; //apollo.longPollQPS, 限制长轮询总qps（长轮询类是一个单例）
//for on error retry
private long onErrorRetryInterval = 1;//无法自定义属性，默认1s，拉取config service地址的失败重试间隔和拉取配置的首次失败重试间隔
private TimeUnit onErrorRetryIntervalTimeUnit = TimeUnit.SECONDS;//1 second，无法自定义的配置
//for typed config cache of parser result, e.g. integer, double, long, etc.
//Abstract缓存里，有方法提供int、double等获取，原始都是字符串，为了降低每次获取都做转换的性能损耗，把这个值缓存起来
private long maxConfigCacheSize = 500;//500 cache数，apollo.configCacheSize
private long configCacheExpireTime = 1;//1 minute 缓存过期时间
private TimeUnit configCacheExpireTimeUnit = TimeUnit.MINUTES;//1 minute
private long longPollingInitialDelayInMills = 2000;//2 seconds，apollo.longPollingInitialDelayInMills，长轮询请求的中间间隔时间
private boolean autoUpdateInjectedSpringProperties = true; //apollo.autoUpdateInjectedSpringProperties，默认true 初始化时会把指定集合里的配置set到system properties里
private final RateLimiter warnLogRateLimiter; //warn log控制，目前1分钟打1 warn log
private boolean propertiesOrdered = false; //apollo.property.order.enable，默认false，获取到的properties和yml配置项按配置中心顺序
private boolean propertyNamesCacheEnabled = false; //是否缓存配置项的key
private boolean propertyFileCacheEnabled = true; //apollo.cache.file.enable，是否存本地文件，控制LocalFileRepository创建，默认开启
private boolean overrideSystemProperties = true; //apollo.override-system-properties，默认true，是否将某些常用的apollo配置写到system properties
private boolean propertyKubernetesCacheEnabled = false; //apollo.cache.kubernetes.enable，是否加到启用config map缓存
private boolean clientMonitorEnabled = false; //apollo.client.monitor.enabled，是否开启client监控的总开关
private boolean clientMonitorJmxEnabled = false; //apollo.client.monitor.jmx.enabled，jmx监控
private String monitorExternalType = ""; //apollo.client.monitor.external.type，定义导出哪些类型监控
private long monitorExternalExportPeriod = 10; //apollo.client.monitor.external.export-period，监控打点时间间隔
private int monitorExceptionQueueSize = 25; //记录最近收集的25个异常

//判断是否只读本地文件
public boolean isInLocalMode() {
}

//获取apollo env配置
public Env getApolloEnv() {
  return EnvUtils.transformEnv(Foundation.server().getEnvType());
}

//app id
public String getAppId() {
  String appId = Foundation.app().getAppId();
  if (Strings.isNullOrEmpty(appId)) {
    appId = ConfigConsts.NO_APPID_PLACEHOLDER;
    if (warnLogRateLimiter.tryAcquire()) {
      logger.warn(
          "app.id is not set, please make sure it is set in classpath:/META-INF/app.properties, now apollo will only load public namespace configurations!");
    }
  }
  return appId;
}

//获取apollo cache的目录
private String getCustomizedCacheRoot() {
  // 1. Get from System Property
  String cacheRoot = System.getProperty(ApolloClientSystemConsts.APOLLO_CACHE_DIR);
  if (Strings.isNullOrEmpty(cacheRoot)) {
    // 2. Get from OS environment variable
    cacheRoot = System.getenv(ApolloClientSystemConsts.APOLLO_CACHE_DIR_ENVIRONMENT_VARIABLES);
  }
  if (Strings.isNullOrEmpty(cacheRoot)) {
    // 3. Get from server.properties
    cacheRoot = Foundation.server().getProperty(ApolloClientSystemConsts.APOLLO_CACHE_DIR, null);
  }
  if (Strings.isNullOrEmpty(cacheRoot)) {
    // 4. Get from app.properties
    cacheRoot = Foundation.app().getProperty(ApolloClientSystemConsts.APOLLO_CACHE_DIR, null);
  }
  if (Strings.isNullOrEmpty(cacheRoot)) {
    // 5. Get from deprecated config
    cacheRoot = getDeprecatedCustomizedCacheRoot();
  }
  return cacheRoot;
}

//获取cache的k8s的namespace
private String getCacheKubernetesNamespace() {
  // 1. Get from System Property
  String k8sNamespace = System.getProperty(ApolloClientSystemConsts.APOLLO_CACHE_KUBERNETES_NAMESPACE);
  if (Strings.isNullOrEmpty(k8sNamespace)) {
    // 2. Get from OS environment variable
    k8sNamespace = System.getenv(ApolloClientSystemConsts.APOLLO_CACHE_KUBERNETES_NAMESPACE_ENVIRONMENT_VARIABLES);
  }
  if (Strings.isNullOrEmpty(k8sNamespace)) {
    // 3. Get from server.properties
    k8sNamespace = Foundation.server().getProperty(ApolloClientSystemConsts.APOLLO_CACHE_KUBERNETES_NAMESPACE, null);
  }
  if (Strings.isNullOrEmpty(k8sNamespace)) {
    // 4. Get from app.properties
    k8sNamespace = Foundation.app().getProperty(ApolloClientSystemConsts.APOLLO_CACHE_KUBERNETES_NAMESPACE, null);
  }
  return k8sNamespace;
}
```



Spring Boot支持的apollo配置：

```properties
# apollo 应用id
app.id=
# apollo label，灰度配置时用上
apollo.label=
# apollo集群
apollo.cluster=
# apollo缓存目录
apollo.cache-dir=
# apollo连config的secret
apollo.access-key.secret=
# apollo meta server地址
apollo.meta=
# apollo 配置服务地址
apollo.config-service=
# 是否将properties/yml文件的配置项下发时排序保持一致
apollo.property.order.enable
# 是否缓存配置名，供propertiesSource使用
apollo.property.names.cache.enable
# 是否覆盖system properties
apollo.override-system-properties
# 定义导出哪些类型监控，比如"prometheus"
apollo.client.monitor.external.type
# 是否开启client监控的总开关
apollo.client.monitor.enabled
# 监控打点时间间隔
apollo.client.monitor.external.export-period
# 是否开启JMX监控
apollo.client.monitor.jmx.enabled
#记录最近收集的异常个数
apollo.client.monitor.exception-queue-size
```



# server.properties配置

`server.properties`支持的配置

```properties
# 集群
idc=
# 环境
env=
# meta server地址
apollo.meta=
# config服务地址
apollo.config-service
# apollo缓存目录
apollo.cache-dir=

apollo.cache.kubernetes.namespace=


```

server.properties支持自定义配置，通过DefaultServerProvider#getProperty获取。



# app.properties配置



app.properties支持这些配置：

```properties
app.id=
apollo.label=
apollo.access-key.secret=
# meta server地址
apollo.meta=
# config服务地址
apollo.config-service=
# k8s缓存配置的namespace
apollo.cache.kubernetes.namespace=
# 是否将properties/yml文件的配置项下发时排序保持一致
apollo.property.order.enable=
```

app.properties也支持自定义配置，通过DefaultApplicationProvider#getProperty获取。

server.properties和app.properties涉及的配置。



# NetworkProvider

NetworkProvider的默认实现DefaultNetworkProvider，支持获取服务器ip和服务器名。



# 总结

本文介绍了apollo client的配置。我们怎么改这些配置呢？一般来说最高优先级是java VM启动参数，也就是说通过-Dxxx=xxx自定义配置值。

通常建议联系Leon He确认后再进行更改。