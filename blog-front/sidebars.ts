import type { SidebarsConfig } from '@docusaurus/plugin-content-docs'

const sidebars: SidebarsConfig = {
  'ai-arch': [
    'ai-arch/introduction',
    'ai-arch/cpp/cpp',

  ],
  k8s: [
    'k8s/introduction',
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
