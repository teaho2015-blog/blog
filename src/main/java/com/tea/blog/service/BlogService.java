/**
 * @author teaho2015@gmail.com
 * since 2017/1/10
 */
package com.tea.blog.service;

import com.tea.blog.exception.NotFoundException;
import com.tea.blog.vo.BlogVO;
import com.tea.frame.jdbc.support.Page;
import com.tea.blog.domain.Blog;
import com.tea.blog.dto.BlogDTO;

public interface BlogService {
    Page getPage(int pageNum) throws NotFoundException;

    Page getPage(int pageNum, int pageSize, boolean checkEmpty) throws NotFoundException;

    Blog getArticle(String id) throws NotFoundException;

    BlogVO getArticleAttachElderId(String id) throws NotFoundException;

    Blog getElderArticle(String id) throws NotFoundException;

}
