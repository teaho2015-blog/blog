/**
 * @author teaho2015@gmail.com
 * since 2017/1/10
 */
package com.tea.blog.service;

import com.tea.common.exception.NotFoundException;
import com.tea.blog.vo.BlogVO;
import com.tea.util.jdbc.support.Page;
import com.tea.blog.domain.Blog;

public interface BlogService {
    Page<Blog> getPage(int pageNum) throws NotFoundException;

    Page<Blog> getPage(int pageNum, int pageSize, boolean checkEmpty) throws NotFoundException;

    Blog getArticle(String id) throws NotFoundException;

    BlogVO getArticleAttachElderId(String id) throws NotFoundException;

    Blog getElderArticle(String id) throws NotFoundException;

}
