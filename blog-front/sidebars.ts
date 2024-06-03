import type { SidebarsConfig } from '@docusaurus/plugin-content-docs'

const sidebars: SidebarsConfig = {
  'ai-arch': [
    'ai-arch/introduction',
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
