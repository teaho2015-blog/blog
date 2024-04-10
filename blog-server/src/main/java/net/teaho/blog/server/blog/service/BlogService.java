/**
 * @author teaho2015@gmail.com
 * since 2017/1/10
 */
package net.teaho.blog.server.blog.service;

import net.teaho.blog.server.blog.domain.Blog;
import net.teaho.blog.server.blog.vo.BlogVO;
import net.teaho.blog.server.common.exception.NotFoundException;
import net.teaho.blog.server.util.jdbc.support.Page;
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

    void updateBlog(Blog blog);
}
