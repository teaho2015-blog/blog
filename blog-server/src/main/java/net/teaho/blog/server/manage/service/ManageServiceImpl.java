/**
 * @author teaho2015@gmail.com
 * since 2016/11/16
 */
package net.teaho.blog.server.manage.service;

import net.teaho.blog.server.common.Constants;
import net.teaho.blog.server.blog.dao.BlogDAO;
import net.teaho.blog.server.blog.domain.Blog;
import net.teaho.blog.server.util.DateUtil;
import net.teaho.blog.server.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Component
public class ManageServiceImpl implements ManageService {
    @Autowired
    private BlogDAO blogDAO;


    @Override
    public void newBlog(Blog blog, MultipartFile image, HttpServletRequest request)throws IOException {
//        User user = UserUtil.getCurrentLoginUser(request);
        blog.setId(UUIDGenerator.generateUUID());
        if (image != null&&image.getSize()!=0) {
            String rpath = request.getSession().getServletContext()
                    .getRealPath("");
            String separator = File.separator;
            String orginalFileName = image.getOriginalFilename();
            StringBuffer path = new StringBuffer();
            path.append(rpath);
            path.append(Constants.BLOG.DEFAULT_HEAD_IMAGE_FILEPATH);
            //set file name
            String filePath = path.toString() + separator + blog.getId() + orginalFileName.substring(orginalFileName.lastIndexOf("."),orginalFileName.length());
            File f = new File(path.toString());
            if (!f.exists()) {
                f.mkdirs();
            }
            FileCopyUtils.copy(image.getBytes(), new File(filePath));

            String image_url = filePath.replace(rpath, "");
//            image_url = image_url.substring(1);
            image_url = image_url.replace(File.separator, "/");
            blog.setImageUrl(image_url);

        } else if (blog.getImageUrl() ==null||"".equals(blog.getImageUrl())){
            blog.setImageUrl(Constants.BLOG.DEFAULT_IMAGE_URL);
        }
        blog.setDate(DateUtil.getCurrentTimestamp());
        blog.setCreatorId(Constants.USER.ID);
        blog.setCreatorName(Constants.USER.NAME);
        blog.setCreateTime(DateUtil.getCurrentTimestamp());
        blog.setDeleteFlag(0);
        blogDAO.add(blog);
    }


    public BlogDAO getBlogDAO() {
        return blogDAO;
    }

    public void setBlogDAO(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }
}
