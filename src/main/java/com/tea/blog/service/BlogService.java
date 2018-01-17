/**
 * @author teaho2015@gmail.com
 * since 2017/1/10
 */
package com.tea.blog.service;

import com.tea.Constants;
import com.tea.common.exception.NotFoundException;
import com.tea.blog.vo.BlogVO;
import com.tea.util.jdbc.support.Page;
import com.tea.blog.domain.Blog;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface BlogService {
    Page<Blog> getPage(int pageNum) throws NotFoundException;

    Page<Blog> getPage(int pageNum, int pageSize, boolean checkEmpty) throws NotFoundException;

    Blog getArticle(String id) throws NotFoundException;

    BlogVO getArticleAttachElderId(String id) throws NotFoundException;

    Blog getElderArticle(String id) throws NotFoundException;

    void deleteArticle(String id);

    /**
     *
     * @param image
     * @return   link string
     */
    String addImage(HttpServletRequest request, MultipartFile image) throws IOException;

    void createBlog(Blog blog);


}
