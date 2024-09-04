import type { SidebarsConfig } from '@docusaurus/plugin-content-docs'

const sidebars: SidebarsConfig = {
  'ai-arch': [
    'ai-arch/introduction',
    'ai-arch/cpp/cpp',

  ],
  k8s: [
    'k8s/introduction',
    'k8s/install/k8s-install',
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
  tools: [
    {
      label: 'test',
      type: 'link',
      href: 'https://blog.teaho.net',

    },
  ],
}

module.exports = sidebars
