---
id: es-knn
slug: /ai-arch/elasticsearch/es-knn
title: ES向量搜索和Lucene执行原理
---

## 简介

2019年Elasticsearch 7.0 版本发布，正式开始增加对向量字段的支持，字段类型新增了 dense_vector 类型。  
2022年Elasticsearch发布8.0版本，增加knn_search接口和KNN搜索功能。  
Elasticsearch 8.7版本后将该查询方式融入_search里，key为knn。

k近邻查找三要素：距离度量、k值的选择和分类决策规则。
在ES的knn搜索中，我们可以控制前两项。那么我们看一下ES向量检索有什么能力，以及它是怎么做的。

## 创建向量索引
我们通过在ES索引中增加dense_vector类型字段，支持向量的存储和查询。

我们看下dense_vector字段的重要属性：
- element_type：float（每维度4字节），byte（每维度1字节），bit（每维度1位，维数必须8的倍数）
- Dims 维度
- similarity 距离度量，默认为cosine，有这几个选项l2_norm、dot_product、cosine、max_inner_product。
- index_options.type 有几种选择：hnsw、int8_hnsw、int4_hnsw、bbq_hnsw、flat、int8_flat、int4_flat、bbq_flat。
  - 分为hnsw(层次化可导航小世界)和flat（暴力查找）两种索引类型。
  - int8相当于把向量每一维度压缩到1字节，int4压缩到半个字节，bbq压缩到1位精度。
- index_options.m，HNSW 图中每个节点将连接到的邻居数量。默认16。
- index_options.ef_construction，组装每个新节点的近邻列表时要trace的候选者数量。

如下是一个创建ES向量字段的方式。
~~~~http request
PUT test-vector-index
{
  "mappings": {
      "properties": {
        "content": {
          "type": "text",
          "fields": {
            "keyword": {
              "type": "keyword",
              "ignore_above": 256
            }
          }
        },
        "embedding": {
          "type": "dense_vector",
          "dims": 3,
          "index": true,
          "similarity": "cosine",
          "index_options": {
            "type": "int8_hnsw",
            "m": 16,
            "ef_construction": 100
          }
        },
        "id": {
          "type": "text",
          "fields": {
            "keyword": {
              "type": "keyword",
              "ignore_above": 256
            }
          }
        }
    }
  }
}
~~~~

## 执行搜索

~~~~
// 插入测试数据
POST test-vector-index/_bulk?refresh=true
{ "index": { "_id": "1" } }
{ "embedding": [1, 5, -20], "content": "moose family", "id": "1" }
{ "index": { "_id": "2" } }
{ "embedding": [42, 8, -15], "content": "alpine lake", "id": "2" }
{ "index": { "_id": "3" } }
{ "embedding": [15, 11, 23], "content": "full moon", "id": "3" }

~~~~


es支持两种knn搜索方式：
1. 使用knn选项，进行ANN近似近邻搜索。
2. 用带了向量函数的script_score查询，进行精确KNN查询。

### knn选项查询
Elasticsearch在_search API的knn项中支持Approxiate knn搜索。
重要属性：
- filter： 过滤匹配文档，语法同一般query.filter。
- k：topk，返回多少个近邻。
- num_candidates：每个分片的候选数。
- query_vector：查询向量。
- similarity: 距离度量的方法，l2_norm（欧几里德距离）、cosine余弦相似、dot_product点积、max_inner_product。
  ~~~
  POST test-vector-index/_search
  {
    "knn": {
    "field": "embedding",
    "query_vector": [-5, 9, -12],
    "k": 1,
    "num_candidates": 100
    }
  }

  ~~~
注：ES将knn搜索放在_search下，其实我们可以进行knn搜索同时通过query搜索去做交集搜索。

### script_score进行精确查询
> https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-script-score-query.html#vector-functions

~~~~http request

GET test-vector-index/_search
{
  "query": {
    "script_score": {
      "query" : {
        "match_all": {}
      },
      "script": {
        "source": "cosineSimilarity(params.query_vector, 'embedding') + 1.0", 
        "params": {
          "query_vector": [4, 3.4, -0.2]  
        }
      }
    }
  }
}

~~~~


## 能力总结

ES作为搜索引擎，密锣紧鼓的网罗了业界各类流行搜索能力。比如除了knn搜索能力，我发现ES还支持Time Serial的索引。
我们谈谈ES的Knn能力，首先在其公布的官博上。他们只支持HNSW相似搜索索引算法(层次化可导航小世界)和暴力搜索。
1. 首先，HNSW在ANN-Benchmarks中展示了优秀的基准测试能力。
2. HNSW在工业界广泛使用。

同时它，支持了一些调节参数，使我们可以在内存压缩、性能和相似度中做权衡。
但要说的是，8.0只支持HNSW和暴力精确搜索，我认为是有待加强的。因为LSH、IVF等都有其优势。

## 执行源码分析



## Reference

[1]dense-vector：https://www.elastic.co/guide/en/elasticsearch/reference/current/dense-vector.html  
[2]knn-search：https://www.elastic.co/guide/en/elasticsearch/reference/current/knn-search.html  
[3]Introducing approximate nearest neighbor search in Elasticsearch 8.0  
[4]HNSW论文：https://arxiv.org/abs/1603.09320  





