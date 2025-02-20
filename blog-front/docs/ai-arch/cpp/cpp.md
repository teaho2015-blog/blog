---
id: cpp
slug: /ai-arch/cpp
title: c++的基础知识梳理
---

## 简介

前阵子学习了解了cpp，对其语法和基础库特点，结合自己在项目中的使用，做了常用知识点整理。  
方便形成体系的去对c++的能力加深印象，平常我们也可按照这个树去查漏补缺。

## 特性梳理和总结

> C++ 11

![](cpp_mm.png)

## 基本安装
sudo apt update
sudo apt install aptitude
sudo aptitude install gcc g++

vscode for linux
https://zhuanlan.zhihu.com/p/344940452
https://zhuanlan.zhihu.com/p/618043511

C++跨linux发行版
https://blog.csdn.net/wqfhenanxc/article/details/106191068

### 打包和部署

静态库和动态库：
https://www.runoob.com/w3cnote/cpp-static-library-and-dynamic-library.html


包管理工具: [conan](http://chu-studio.com/posts/2019/%E4%BB%8E%E9%9B%B6%E5%BC%80%E5%A7%8B%E7%9A%84C++%E5%8C%85%E7%AE%A1%E7%90%86%E5%99%A8CONAN%E4%B8%8A%E6%89%8B%E6%8C%87%E5%8D%97)


## c++排查问题工具栈

### 调试

一般使用gdb或者gdbserver。

远程服务器编译时，记得加-g参数
   g++ helloworld.cpp -g
   或者cmake增加 cmake -DCMAKE_BUILD_TYPE=Debug标记。

用gdbserver启动：（gdb打断点命令查文档）
gdbserver :12345 ./a.out


### 问题排查工具

| 工具                   | 	作用           |
|----------------------|---------------|
| Valgrind / heaptrack | 查看内存泄漏问题、越界访问 |
| perf                 | 方法追踪          |
| perf +  FlameGraph   | 可以查火焰图        |
| GDB                  |         附加到运行进程，查线程栈也可      |
| strace               | 跟踪系统调用 |














