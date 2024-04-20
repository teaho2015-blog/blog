import type { Config } from '@docusaurus/types'
import type * as Preset from '@docusaurus/preset-classic'
import { themes } from 'prism-react-renderer'
import { GiscusConfig } from './src/components/Comment'
import social from './data/social'


const config: Config = {
  title: "Tea's Blog",
  url: 'https://blog.teaho.net',
  baseUrl: '/',
  favicon: 'img/logo.png',
  organizationName: 'teaho.net',
  projectName: 'blog',
  customFields: {
    bio: "Tea's Blog",
    description:
      "Tea's Blog",
  },
  onBrokenLinks: "warn",
  // themes: ['@docusaurus/theme-search-algolia'],
  themeConfig: {
    // announcementBar: {
    //   id: 'announcementBar-3',
    //   content: ``,
    // },
    metadata: [
    ],
    docs: {
      sidebar: {
        hideable: true,
      },
    },
    navbar: {
      title: "Tea's Blog",
      logo: {
        alt: "Tea's Blog",
        src: 'img/logo.png',
        // srcDark: 'img/logo.webp',
      },
      hideOnScroll: true,
      items: [
        // {
        //   label: 'Blog',
        //   position: 'left',
        //   to: '/',
        // },
        {
          label: '合集',
          position: 'right',
          to: "/collections",
          items: [
            { label: 'Spring、Spring Boot、Spring Cloud剖析', href: 'https://spring-source-code-learning.gitbook.teaho.net' },
            { label: 'Design Pattern分析', href: 'https://design-pattern-learning.gitbook.teaho.net' },
            { label: 'Java8基础API分析', href: 'https://java-api-learning.gitbook.teaho.net' },
            { label: 'Java开发知识点总结', href: 'https://offer-prepare.teaho.net' },
            { label: '环游中国多省的一些记录', href: 'https://travel-around-china.gitbook.teaho.net' },
            { label: 'Mysql query optimization summary', href: 'https://mysql.gitbook.teaho.net' },
            { label: 'dubbo一些笔记', href: 'https://dubbo-learning.gitbook.teaho.net' },


          ],
        },
        {
          label: '归档',
          position: 'right',
          to: 'archive',
        },
        {
          label: '关于/联系',
          position: 'right',
          to: 'about',
        },
         {
          label: '更多',
          position: 'right',
          items: [
            { label: 'changelog', to: 'changelog' },
          ],
        },
        {
          type: 'search',
          position: 'right',
        },
        // {
        //   label: '更多',
        //   position: 'right',
        //   items: [
        //     { label: '归档', to: 'archive' },
        //     // { label: '笔记', to: 'docs/skill' },
        //     // { label: '资源', to: 'resources' },
        //     // { label: '友链', to: 'friends' },
        //     // { label: '工具推荐', to: 'docs/tools' },
        //   ],
        // },
      ],
    },
    footer: {
      style: 'dark',
      links: [
        {
          title: '站内',
          items: [
            { label: 'Blog', to: '/' },
            { label: 'Archive', to: 'archive' },
            { label: '合集', to: 'collections' },

          ],
        },
        {
          title: '联系',
          items: [
            { label: '关于我', position: 'right', to: '/about' },
            { label: 'GitHub', href: social.github.href },
            {
              html: `
                  <a href="mailto:teaho2015@gmail.com" title="gmail" target="_blank" rel="noreferrer noopener" aria-label="gmail">
                    <img src="https://cdn.simpleicons.org/Gmail#EA4335" alt="Deploys by Netlify" width="25" height="25" />
                  </a>
                `,
            },
            {
              html: `
                  <div style="top:50%;">
                  <img src="https://cdn.simpleicons.org/wechat##07C160" alt="wechat:hotingleong" width="15" height="15" /> hotingleong

                  </div>
                `,
            },


          ],
        },
        {
          title: '条款',
          items: [
            {
              html: `
                  <a href="https://www.netlify.com" target="_blank" rel="noreferrer noopener" aria-label="Deploys by Netlify">
                    <img src="https://www.netlify.com/img/global/badges/netlify-color-accent.svg" alt="Deploys by Netlify" width="114" height="51" />
                  </a>
                `,
            },
          ],
        },
      ],

      copyright: `
        <p>Copyright © Tea's Blog 2016-${new Date().getFullYear()}.All rights reserved. My works are licensed under CC BY-NC-SA 4.0 .</p>
        `,
    },
    algolia: {
      appId: 'GVPKO1Q921',
      apiKey: '1de910ab6530a04cc51eeb9dd60786a3',
      indexName: 'teaho',
    },
    prism: {
      theme: themes.oneLight,
      darkTheme: themes.oneDark,
      additionalLanguages: [
        'bash',
        'json',
        'java',
        'python',
        'php',
        'graphql',
        'rust',
        'toml',
        'protobuf',
      ],
      defaultLanguage: 'javascript',
      magicComments: [
        {
          className: 'theme-code-block-highlighted-line',
          line: 'highlight-next-line',
          block: { start: 'highlight-start', end: 'highlight-end' },
        },
        {
          className: 'code-block-error-line',
          line: 'This will error',
        },
      ],
    },
    giscus: {
      repo: 'teaho2015-blog/blog',
      repoId: 'MDEwOlJlcG9zaXRvcnk4Mjg0MzI2OA==',
      category: 'General',
      categoryId: 'DIC_kwDOBPAWhM4Cegj6',
      theme: 'light',
      darkTheme: 'noborder_dark',
      loading: "lazy",
      lang: "en",
    } satisfies Partial<GiscusConfig>,
    tableOfContents: {
      minHeadingLevel: 2,
      maxHeadingLevel: 4,
    },
    liveCodeBlock: { playgroundPosition: 'top' },
    zoom: {
      selector: '.markdown :not(em) > img',
      background: {
        light: 'rgb(255, 255, 255)',
        dark: 'rgb(50, 50, 50)',
      },
    },
    colorMode: {
      defaultMode: 'dark',
      disableSwitch: true,
      respectPrefersColorScheme: false,
    },
  } satisfies Preset.ThemeConfig,
  presets: [
    [
      'classic',
      {
        docs: {
          path: 'docs',
          sidebarPath: 'sidebars.ts',
        },
        blog: false, // doc only mode, Optional: disable the blog plugin
        // blog: {
        //   routeBasePath: '/', // Serve the blog at the site's root
        //   /* other blog options */
        // },
        theme: {
          customCss: ['./src/css/custom.scss'],
        },
        sitemap: {
          priority: 0.5,
        },
        gtag: {
          trackingID: 'G-0T8ST6E0FN',
          anonymizeIP: false,
        },
        debug: true,
      } satisfies Preset.Options,
    ],
  ],
  plugins: [
    'docusaurus-plugin-image-zoom',
    'docusaurus-plugin-sass',
    '@docusaurus/plugin-ideal-image',
    ['docusaurus-plugin-baidu-tongji', { token: '751fad25d8bfffcee48a1a764be9d401' }],
    [
      '@docusaurus/plugin-pwa',
      {
        debug: process.env.NODE_ENV === 'development',
        offlineModeActivationStrategies: ['appInstalled', 'standalone', 'queryString'],
        pwaHead: [
          { tagName: 'link', rel: 'icon', href: '/img/logo.png' },
          { tagName: 'link', rel: 'manifest', href: '/manifest.json' },
          { tagName: 'meta', name: 'theme-color', content: '#12affa' },
        ],
      },
    ],
    [
      './src/plugin/plugin-content-blog', // 为了实现全局 blog 数据，必须改写 plugin-content-blog 插件
      {
        routeBasePath: '/',
        editUrl: ({ locale, blogDirPath, blogPath, permalink }) =>
          `https://github.com/teaho2015-blog/blog/edit/main/${blogDirPath}/${blogPath}`,
        editLocalizedFiles: false,
        blogDescription: 'test',
        blogSidebarCount: 10,
        blogSidebarTitle: 'Blogs',
        postsPerPage: 10,
        showReadingTime: true,
        readingTime: ({ content, frontMatter, defaultReadingTime }) =>
          defaultReadingTime({ content, options: { wordsPerMinute: 300 } }),
        feedOptions: {
          type: 'all',
          title: 'teaho2015',
          copyright: `Copyright © ${new Date().getFullYear()} teaho2015 Built with Docusaurus.`,
        },
      },
    ],
  ],
  headTags: [
    {
      tagName: 'meta',
      attributes: {
        name: 'description',
        content: 'headTagsContent',
      },
    },
  ],
  stylesheets: [
    'https://cdn.jsdelivr.net/npm/misans@4.0.0/lib/Normal/MiSans-Normal.min.css',
    'https://cdn.jsdelivr.net/npm/misans@4.0.0/lib/Normal/MiSans-Semibold.min.css',
  ],
  // i18n: {
  //   defaultLocale: 'en',
  //   locales: ['en'],
  //   localeConfigs: {
  //     // en: {
  //     //   htmlLang: 'en-GB',
  //     // },
  //   },
  // },
}

export default config
