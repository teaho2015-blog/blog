---
id: k8s-install
slug: /k8s/install
title: Kubernetes安装
date: 2024-06-21
authors: teaho2015
---

# Kubernetes安装

官网介绍几种平时学习的部署方式，minikube、kind、kubeadm。

* **minikube** 单机的k8s部署安装工具。占用资源比较小，部署也方便。
* **kind** 单机k8s安装工具，以镜像的形式构建，最方便。
* **kubeadm** 标准的安装方式，算是比较便捷的生产级k8s集群安装管理工具。

## minikube

> ubuntu22.04环境

1. 安装kubelet和kuberctl

~~~bash
sudo apt-get update
sudo apt-get install -y kubelet kubeadm kubectl
sudo apt-mark hold kubelet kubeadm kubectl
~~~

2. 安装minikube

~~~bash
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
sudo install minikube-linux-amd64 /usr/local/bin/minikube && rm minikube-linux-amd64
~~~

3. 启动minikube和dashboard

最理想是有全局的代理，直接`minikube start`和`minikube dashboard`。

其次使用指定代理，采用官网FAQ的指定镜像库方式。
`minikube start --image-repository='registry.cn-hangzhou.aliyuncs.com/google_containers'
--image-mirror-country=cn \`，  
`minikube dashboard`。

### 查看状态

kubectl get pods --all-namespaces

kubectl logs --namespace=kubernetes-dashboard dashboard-metrics-scraper-b5fc48f67-m5v96

### 查看日志

`minikube logs`


### ImagePullBackOff错误处理

1. 我用指定代理踩了很多坑， 首先尝试改仓库。
  `minikube start --registry-mirror=https://registry.docker-cn.com \
    --image-mirror-country=cn \`。 

2. 不行的话，指定minikube的docker代理环境变量。  
   ~~~
   minikube start --docker-env HTTP_PROXY=http://127.0.0.1:7890 \  
   --docker-env HTTPS_PROXY=http://127.0.0.1:7890 \  
   --docker-env NO_PROXY=localhost,127.0.0.1,10.96.0.0/12,192.168.99.0/24,192.168.39.0/24 \  
   --docker-env ALL_PROXY=socks5://127.0.0.1:7890 \  
   --registry-mirror=https://registry.docker-cn.com \  
   --image-mirror-country=cn \  
   --driver=docker
   ~~~  
3. 再不行，曲线救国，
   1. 使用docker命令把缺失的镜像拉下来，比如
      * docker pull docker.io/kubernetesui/metrics-scraper:v1.0.8
      * docker pull docker.io/kubernetesui/dashboard:v2.7.0
   2. 使用minikube image load让minikube加载镜像
   3. 重新启动minikube

题外话，docker代理可用指定环境变量http_proxy、https_proxy 或者更改`/etc/docker/daemon.json`的仓库源。

### 实用命令参数

* minikube logs：查看运行日志。
* minikube image： minikube内拉/推镜像的方式。
* minikube options: 有一些使用的指定日志目录等参数。
* minikube start:
  * --network-plugin指定cni插件
  * --kubernetes-version指定版本
  * -p 指定profile，minikube可以启动多集群。
  * --driver=docker 指定minikube的部署驱动。
  * --docker-env 指定docker的环境变量。


