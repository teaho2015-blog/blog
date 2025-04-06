---
id: consul-deploy
slug: /ai-arch/consul/consul-deploy
title: Consul部署
---

## 单节点部署运行

### 源码安装

https://developer.hashicorp.com/consul/docs/install#compiling-from-source

首先执行，打包可执行文件。
~~~
export GOOS=linux GOARCH=amd64
make dev
~~~

两种方式启动consul server

1. 快捷启动的方式
~~~~
./bin/consul agent -dev
~~~~

2. 指定参数启动服务
~~~~
./bin/consul agent -disable-host-node-id=true -bind=0.0.0.0 -client=0.0.0.0 -retry-join=${SERVER_NODE_LIST_FROM_DOMAIN[0]} -retry-join=${SERVER_NODE_LIST_FROM_DOMAIN[1]} -retry-join=${SERVER_NODE_LIST_FROM_DOMAIN[2]} -node=${HOST_NAME} -data-dir=/home/work/data/consul_${CLUSTER_NAME}_${DATA_CENTER_NAME}.d -datacenter=${DATA_CENTER_NAME} > /home/work/log/feeds-consul/consul.stdout.log  2>/home/work/log/feeds-consul/consul.stderr.log
~~~~
-data-dir：数据目录  
-datacenter：数据中心的名称  
-bind：绑定ip  

### 快捷启动


`./bin/consul agent -dev`


## docker部署



## Ppconsul 安装
github库：https://github.com/oliora/ppconsul?tab=readme-ov-file

~~~
cd ppconsul
mkdir build && cd build
cmake ..
make
sudo make install
~~~


## Reference
https://blog.csdn.net/weixin_50448879/article/details/136731875
https://juejin.cn/post/7027839175793573918





