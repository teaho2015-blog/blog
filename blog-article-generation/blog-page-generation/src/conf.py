from datetime import datetime, date

from sqlalchemy import DateTime

from table import B_Blog

conf = {
    "domain": "",
    "article_path": "/blog-article-generation/blog-page-generation/article/",
    "article_file_name": "article-{id}.html",
    "article_file_path": "/home/teaho/IdeaProjects/mine/blog/blog-article-generation/blog-page-generation/article/",
    "archive_file_path": "/home/teaho/IdeaProjects/mine/blog/blog-article-generation/blog-page-generation/",
    "home_file_path": "/home/teaho/IdeaProjects/mine/blog/blog-article-generation/blog-page-generation/home/"
}


def extra_blogs():
    blogs = []

    blog = B_Blog()
    blog.ID = '2fc086e7f8b142acadd0977e5f1cbd3d'
    blog.IMAGE_URL = ''
    blog.DATE = date(2019, 9, 9)
    blog.TITLE = 'dubbo的一些学习笔记'
    blog.TITLE_SECONDARY = '工作中对一些Dubbo问题的学习'
    blog.CONTENT = ''
    blog.TYPE = 2
    blog.EXTERNAL_URL = 'https://dubbo-learning.gitbook.teaho.net'
    blog.CREATOR_ID = 'SYSTEM'
    blog.CREATOR_NAME = 'SYSTEM'
    blog.CREATE_TIME = datetime(2019, 9, 9, 20, 49, 59)
    blog.DELETE_FLAG = 0
    blogs.append(blog)

    blog2 = B_Blog()
    blog2.ID = '0773aacbcfa9478bbaab119da3076af0'
    blog2.IMAGE_URL = ''
    blog2.DATE = date(2018, 6, 5)
    blog2.TITLE = '《高性能MySQL》的笔记'
    blog2.TITLE_SECONDARY = '总结《高性能MySQL》的一个笔记和少量涉及个人经验、文档参考和其他一些书的分享'
    blog2.CONTENT = ''
    blog2.TYPE = 2
    blog2.EXTERNAL_URL = 'https://mysql.gitbook.teaho.net'
    blog2.CREATOR_ID = 'SYSTEM'
    blog2.CREATOR_NAME = 'SYSTEM'
    blog2.CREATE_TIME = datetime(2018, 6, 5, 20, 30, 45)
    blog2.DELETE_FLAG = 0
    blogs.append(blog2)
    return blogs


if __name__ == '__main__':
    print(str(extra_blogs()[0].CREATE_TIME))
