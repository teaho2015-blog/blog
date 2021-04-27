# -*- coding: utf-8 -*-
import codecs
import json

from bs4 import BeautifulSoup
from sqlalchemy import *
from table import *
from conf import *


# from util import takeWin, takeGDP


def getData():
    session = Session_class()
    data = session.query(B_Blog).filter(B_Blog.DELETE_FLAG == 0).order_by(B_Blog.CREATE_TIME.desc()).all()

    for elem in data:
        print(elem.ID)

    return data

def insertConfBlog(data):
    for elem in extra_blogs():
        left = 0
        for i, row in enumerate(data):
            left = i
            if row.CREATE_TIME < elem.CREATE_TIME:
                print(row.CREATE_TIME)
                break
        data.insert(left, elem)
    return data

def genArchivePage(data):
    input_file = codecs.open("../archive-template.html", mode="r", encoding="utf-8")
    html_str = input_file.read()
    # BeautifulSoup：解析页面
    # lxml：解析器
    soup = BeautifulSoup(html_str, 'lxml')
    template_item = soup.select_one('div.template.hidden .post-preview')
    # print(type(template_item))

    article_file = codecs.open("../archive-article-template.html", mode="r", encoding="utf-8")
    article_html_str = article_file.read()
    article_soup = BeautifulSoup(article_html_str, 'lxml')

    result_arr = []
    for elem in data:
        item = template_item.__copy__()
        item['data-id'] = elem.ID
        # item['class'] = ['post']
        item.select_one('p.post-meta')['data-time'] = elem.CREATE_TIME
        item.select_one('p.post-meta').append(str(elem.DATE).replace('-', '/'))
        if elem.TYPE == 1:
            item.select_one('a')['href'] = conf['domain'] + conf['article_path'] + conf['article_file_name'].replace('{id}', elem.ID)
        elif elem.TYPE == 2:
            item.select_one('a')['href'] = elem.EXTERNAL_URL

        item.select_one('a .post-title').string = elem.TITLE
        item.select_one('a .post-subtitle').string = elem.TITLE_SECONDARY

        # print(item)
        result_arr.append(item)
        # no need to print external article
        if elem.TYPE == 2:
            continue
        article_page = article_soup.__copy__()
        article_wrapper = article_page.select_one("article.page")
        article_wrapper['data-id'] = elem.ID
        article_wrapper.select_one('div.big-image')['style'] = "background-image: url({img_url})".replace("{img_url}", elem.IMAGE_URL)
        article_wrapper.select_one('div.content h3 time').append(str(elem.DATE))
        article_wrapper.select_one('div.content h1.title').append(elem.TITLE)
        article_wrapper.select_one('div.content h2.description').append(elem.TITLE_SECONDARY)
        article_wrapper.select_one('div.content div.text').append(BeautifulSoup(elem.CONTENT, 'lxml'))
        # # 保存为文件
        output_file = codecs.open(conf['article_file_path'] + conf['article_file_name'].replace('{id}', elem.ID), mode="w", encoding="utf-8")
        output_file.write(article_page.prettify())

    page = soup.__copy__()
    for item in result_arr:
        page.select_one("div#article-wrapper").append(item)
    output_file = codecs.open(conf['archive_file_path'] + "archive.html", mode="w", encoding="utf-8")
    output_file.write(page.prettify())
    return


if __name__ == '__main__':
    data = getData()
    data = insertConfBlog(data)
    genArchivePage(data)