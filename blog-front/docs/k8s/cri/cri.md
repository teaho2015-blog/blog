---
id: cri
slug: /k8s/cri
title: 容器运行时
date: 2024-09-01
authors: teaho2015
---

# 容器运行时


SIG-Node团队支持的：kubelet 与 CRI。

![kubelet.png](kubelet.jpg)

kubelet调用下层容器运行时，通过一组**CRI**的gRPC接口来做错。

![kubelet_invoke_CRI.png](kubelet_invoke_CRI.png)