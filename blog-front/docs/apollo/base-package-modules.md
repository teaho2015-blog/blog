---
slug: /apollo/base-package-modules
title: Apollo 基础包模块剖析
date: 2026-01-15
---

## 简介

本文对 Apollo 的核心包及其模块做一些分析，看看里面分别实现了什么功能。

## Apollo-core

```text
├── java.com.ctrip.framework.apollo
│             │   ├── Apollo.java //用于获取Apollo版本
│             │   ├── core //
│             │   │   ├── ApolloClientSystemConsts.java
│             │   │   ├── ConfigConsts.java
│             │   │   ├── dto
│             │   │   │   ├── ApolloConfig.java //apollo下发配置DTO，同时也是client获取远程更新的dto
│             │   │   │   ├── ApolloConfigNotification.java //apollo 长轮询拉取的配置更新通知
│             │   │   │   ├── //省略一些类
│             │   │   │   └── ServiceDTO.java //服务状态封装，portal做探活和client做configservice地址获取时的dto
│             │   │   ├── enums
│             │   │   │   ├── ConfigFileFormat.java //配置文件格式
│             │   │   │   ├── ConfigSyncType.java //同步方式，目前看只全量同步更新
│             │   │   │   ├── ConfigurationChangeType.java //配置更改类型，目前没什么用
│             │   │   │   ├── ConfigurationChangeTypeUtils.java
│             │   │   │   ├── Env.java //环境信息封装
│             │   │   │   └── EnvUtils.java
│             │   │   ├── internals //省略
│             │   │   │   └── LegacyMetaServerProvider.java
│             │   │   ├── MetaDomainConsts.java
│             │   │   ├── schedule //longPolling的策略，默认实现ExponentialSchedulePolicy，成功时固定间隔拉取，失败时间隔会呈指数上升，避免拉跨apollo server
│             │   │   │   ├── ExponentialSchedulePolicy.java
│             │   │   │   └── SchedulePolicy.java
│             │   │   ├── ServiceNameConsts.java
│             │   │   ├── signature //client和configservice的通信签名
│             │   │   │   ├── 省略
│             │   │   ├── spi
│             │   │   │   ├── MetaServerProvider.java //获取metaServer的策略，一般企业自建会覆盖这个SPI
│             │   │   │   └── Ordered.java
│             │   │   └── utils //一些工具类
│             │   └── tracer //监控
│             └── foundation //核心配置包，apollo client/portal用到
│                 ├── 省略……
└── resources
    └── META-INF
        └── services //这几个java service SPI，在client分析章节有说明
            ├── com.ctrip.framework.apollo.core.spi.MetaServerProvider
            ├── com.ctrip.framework.apollo.tracer.spi.MessageProducerManager
            └── com.ctrip.framework.foundation.spi.ProviderManager
```

## Apollo-common

```text
├─java.com.ctrip.framework.apollo.common
│                     ├── aop
│                     │   └── RepositoryAspect.java //链路打点的监控
│                     ├── ApolloCommonConfig.java //1. 提供出去的扫描入口，portal、config、admin会componentScan这个包 2. 注册RequestRejectedHandler
│                     ├── condition //加了些对profile的支持
│                     │   ├── ConditionalOnMissingProfile.java
│                     │   ├── ConditionalOnProfile.java
│                     │   └── OnProfileCondition.java
│                     ├── config //RefreshableConfig是一个抽象类，封装了一些定时刷新配置的策略，实际刷新操作在RefreshablePropertySource子类refresh方法实现
│                     │   ├── RefreshableConfig.java
│                     │   └── RefreshablePropertySource.java
│                     ├── constants
│                     │   ├── AccessKeyMode.java //定义client的密钥模式，FILTER是检验，OBSERVER是只打日志不校验
│                     │   ├── NamespaceBranchStatus.java //namespace的分支状态
│                     │   ├── ReleaseOperation.java //定义了配置发布的9个状态
│                     │   └── //省略
│                     ├── controller //定义了一些通用的mvc controller，mvc异常处理，静态文件缓存事件
│                     ├── datasource //定义了统一的dataSrouce初始化类
│                     ├── dto //省略
│                     ├── entity //省略，核心model已经在各节介绍
│                     ├── exception //省略，几个不同场景的异常
│                     ├── http
│                     │   ├── MultiResponseEntity.java //Apollo封装的response entity，一个Response中包含多个ResponseEntity，比如在longPolling的时候塞如多个namespace的更新通知
│                     │   ├── 省略
│                     ├── jpa
│                     │   ├── H2Function.java
│                     │   └── SqlFunctionsMetadataBuilderContributor.java //注册了NOW函数在HQL里用
│                     └── utils //工具类包
│                         ├── BeanUtils.java //bean工具
│                         ├── GrayReleaseRuleItemTransformer.java //灰度发布规则转换工具，在灰度发布里已解释
│                         ├── UniqueKeyGenerator.java //灰度cluster name就用这个生成
│                         └── //省略
```

## Apollo-biz

```text
└─java.com.ctrip.framework.apollo.biz
                        ├── ApolloBizAssemblyConfiguration.java //在all-in-one部署中整合config配置
                        ├── ApolloBizConfig.java //1. 提供出去的扫描入口，config、admin会componentScan这个包
                        ├── auth
                        │   └── WebSecurityConfig.java //历史兼容，忽略
                        ├── config
                        │   └── BizConfig.java //公共配置类
                        ├── entity //entity包，重点entity在各节介绍
                        ├── eureka
                        │   └── ApolloEurekaClientConfig.java //eureka配置类，继承EurekaClientConfigBean，主要是使用在BizConfig配置的eureka信息覆盖原配置
                        ├── grayReleaseRule //灰度规则，在发布一节分析
                        │   ├── GrayReleaseRuleCache.java
                        │   └── GrayReleaseRulesHolder.java
                        ├── message //
                        │   ├── DatabaseMessageSender.java  //消息发送器，负责将namespace维护关于namespace改动的数据库消息队列
                        │   ├── MessageSender.java  //MessageSender，目前只有DatabaseMessageSender实现
                        │   ├── ReleaseMessageListener.java //release消息监听器接口，实现接口的组件接受并处理release消息
                        │   ├── ReleaseMessageScanner.java //定时扫库里的最新消息，并通知消息监听器们
                        │   └── Topics.java //队列名
                        ├── registry //定义了Apollo自实现的数据库注册发现
                        │   ├── configuration
                        │   │   ├── ApolloServiceDiscoveryAutoConfiguration.java
                        │   │   ├── ApolloServiceRegistryAutoConfiguration.java
                        │   │   └── support
                        │   │       ├── ApolloServiceDiscoveryProperties.java
                        │   │       ├── ApolloServiceRegistryClearApplicationRunner.java
                        │   │       ├── ApolloServiceRegistryDeregisterApplicationListener.java
                        │   │       ├── ApolloServiceRegistryHeartbeatApplicationRunner.java
                        │   │       └── ApolloServiceRegistryProperties.java
                        │   ├── DatabaseDiscoveryClientAlwaysAddSelfInstanceDecoratorImpl.java
                        │   ├── DatabaseDiscoveryClientImpl.java
                        │   ├── DatabaseDiscoveryClient.java
                        │   ├── DatabaseDiscoveryClientMemoryCacheDecoratorImpl.java
                        │   ├── DatabaseServiceRegistryImpl.java
                        │   ├── DatabaseServiceRegistry.java
                        │   ├── package-info.java
                        │   └── ServiceInstance.java
                        ├── repository
                        ├── service
                        │   ├── AccessKeyService.java //accessKey处理封装
                        │   ├── AdminService.java  //提供了App新增、删除操作
                        │   ├── AppNamespaceService.java //App namespace处理封装
                        │   ├── AppService.java //app增删改
                        │   ├── AuditService.java //审计日志service
                        │   ├── BizDBPropertySource.java //提供了拉取数据库里Apollo自身集群用到配置，并填入refreshablePropertySource的能力
                        │   ├── ClusterService.java //集群操作
                        │   ├── CommitService.java //commit操作
                        │   ├── InstanceService.java //客户端实例的增删改service，给页面展示、审计使用
                        │   ├── ItemService.java //配置项增删改
                        │   ├── ItemSetService.java
                        │   ├── NamespaceBranchService.java //拆namespace分支的service，灰度、多cluster namespace实际上使用branch的区隔实现的
                        │   ├── NamespaceLockService.java //namespace锁相关操作
                        │   ├── NamespaceService.java
                        │   ├── ReleaseHistoryService.java //历史记录service
                        │   ├── ReleaseMessageService.java //查发布消息service
                        │   ├── ReleaseService.java
                        │   ├── ServerConfigService.java //portal同步给admin、config服务的服务器配置时，用到这个service
                        │   └── ServiceRegistryService.java //apollo自实现服务注册时，用到此service做服务注册销毁
                        └── utils //
```