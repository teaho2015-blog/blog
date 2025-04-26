import type { SidebarsConfig } from '@docusaurus/plugin-content-docs'

const sidebars: SidebarsConfig = {
  'ai-arch': [
    'ai-arch/introduction',
    'ai-arch/cpp/cpp',
    {
      label: 'Consul',
      type: 'category',
      items: [
        'ai-arch/consul/consul-deploy',
        'ai-arch/consul/consul-feature',

      ],
    },
    {
      label: 'Elasticsearch',
      type: 'category',
      items: [
        'ai-arch/elasticsearch/es-deploy',
        'ai-arch/elasticsearch/es-search-phase',
        'ai-arch/elasticsearch/es-knn',
      ],
    },
    'ai-arch/gpu/head-first-gpu',
    {
      label: '算法',
      type: 'category',
      // link: {
      //   type: 'doc',
      //   id: 'alg /link',
      // },
      items: [
        'ai-arch/alg/dl-base',
        'ai-arch/alg/transformer',

      ],
    },
    {
      label: 'LLM',
      type: 'category',
      link: {
        type: 'doc',
        id: 'ai-arch/llm/llm',
      },
      items: [
        'ai-arch/llm/chatGLM/chatglm-tuning',
        'ai-arch/llm/dify/dify-rag',
        'ai-arch/llm/rag/rag-application',
      ],
    },
  ],
  k8s: [
    'k8s/introduction',
    'k8s/install/k8s-install',
    'k8s/history/history',
    'k8s/container/container',
    'k8s/concept/concept',
    'k8s/pod/pod',
    'k8s/workload/workload-manager',
    'k8s/extend-k8s/extend-k8s',
    'k8s/persistence/persistence',
    'k8s/network/network',
    'k8s/schedule/schedule',
    'k8s/cri/cri',
    'k8s/metric-and-log/metric-and-log',
    'k8s/reference',
  ],
  babel: [
    'babel/introduction',
  ],
  'sys-arch': [
    'sys-arch/introduction',
    'sys-arch/sec-kill-sys/sec-kill-sys',
    'sys-arch/arch-principle/arch-principle',
    'sys-arch/ad-sys/ad-sys',
  ],
  tools: [
    {
      label: 'test',
      type: 'link',
      href: 'https://blog.teaho.net',

    },
  ],
}

module.exports = sidebars
