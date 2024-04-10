/**
 * Created with IntelliJ IDEA.
 * User: teaship
 * Date: 15-4-7
 * Time: 下午4:18
 * To change this template use File | Settings | File Templates.
 */
package net.teaho.blog.server.manage.service;

import net.teaho.blog.server.blog.domain.Blog;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ManageService {
    public void newBlog(Blog blog, MultipartFile image, HttpServletRequest request) throws IOException;
}
