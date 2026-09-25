---
id: introduction
slug: /apollo
title: Apollo 原理剖析
date: 2026-01-15
---

## 关于本系列

本系列文章从部署架构、基础包模块、 Portal/Config/Admin 三大服务的核心流程、 Client 端 SPI 与 Spring 集成等维度，对 Apollo 配置中心做一次自下而上的源码级拆解。文中所有结论均基于 Apollo 官方源码与实际部署经验整理。

## 适用读者

- 想了解 Apollo 内部机制的研发同学
- 正在评估 Apollo 在 k8s/VM 多 IDC 场景下可用性与部署方案
- 需要对 Apollo 做二次开发或定制（如自定义 SPI、灰度规则、注册中心替换等）

## 阅读路径建议

1. **架构视角**：[常见部署架构和可用性](deployment-architecture) → [Apollo 基础包模块剖析](base-package-modules)
2. **Portal 视角**：[Portal 配置资源管理](portal-config-management) → [Portal 配置更新流程](portal-config-update-flow) → [Portal-OpenAPI 机制](portal-openapi)
3. **Admin 视角**：[核心流程 - Admin 配置发布](admin-publish-flow) → [Config - 灰度配置发布](gray-publish)
4. **Config 视角**：[核心流程 - Client 接收配置通知](client-config-notification) → [Apollo Config 原理](config-principle)
5. **Client 视角**：[Apollo Config 原理](config-principle) → [Java Client 配置优先级](java-client-priority) → [Apollo client - SpringBoot 和注解初始化](springboot-init) → [Apollo client - SPI 和 client 配置简析](spi-and-client-config)

## 文档清单

| # | 文档 | 关注点 |
| - | --- | --- |
| 01 | 常见部署架构和可用性 | 单实例 / VM / k8s 多 IDC 部署，可用性分级 |
| 02 | Apollo 基础包模块剖析 | apollo-core / common / biz 包结构与关键类 |
| 03 | Portal 配置资源管理 | App / Cluster / Namespace / Item 等实体关系 |
| 04 | Portal 配置更新流程 | Portal 保存配置 → Item 表的请求链路 |
| 05 | Portal-OpenAPI 机制 | 三方应用调用、ConsumerPermissionValidator 权限校验 |
| 06 | 核心流程 - Admin 配置发布 | ReleaseService#publish、Portal 发布通知 |
| 07 | 核心流程 - Client 接收配置通知 | ReleaseMessage 扫描、NotificationControllerV2、RemoteConfigRepository、RemoteConfigLongPollService |
| 08 | Config - 灰度配置发布 | GrayReleaseRulesHolder、IP/Label 灰度规则 |
| 09 | Java Client 配置优先级 | Spring Boot/Cloud 启动时 Apollo 配置加载顺序 |
| 10 | Apollo client - SpringBoot 和注解初始化 | @EnableApolloConfig、AutoUpdateConfigChangeListener、PropertySourcesProcessor |
| 11 | Apollo client - SPI 和 client 配置简析 | ConfigUtil 配置项、server.properties/app.properties、MetaServerProvider 等 SPI |
| 12 | Apollo Config 原理 | Config 初始化、ConfigService / ConfigFactory / ConfigRepository |

## 参考

- Apollo 官方文档：[https://www.apolloconfig.com/](https://www.apolloconfig.com/)
- Apollo GitHub：[https://github.com/apolloconfig/apollo](https://github.com/apolloconfig/apollo)