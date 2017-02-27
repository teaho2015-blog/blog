/**
 * @author teaho2015@gmail.com
 * since 2016/11/16
 */
package com.tea.manage.service;

import com.tea.frame.util.DateUtil;
import com.tea.frame.util.UUIDGenerator;
import com.tea.blog.dao.BlogDAO;
import com.tea.blog.domain.Blog;
import com.tea.Constants;
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
            path.append(separator);
            path.append("static"+separator+"images"+separator+"blog");
            //set file name
            String filePath = path.toString() + separator + blog.getId() + orginalFileName.substring(orginalFileName.lastIndexOf("."),orginalFileName.length());
//        try {
            File f = new File(path.toString());
            if (!f.exists()) {
                f.mkdirs();
            }
            FileCopyUtils.copy(image.getBytes(), new File(filePath));

//        } catch (IOException e) {
//            e.printStackTrace();//有待改进应输出错误
//        }
            String image_url = filePath.replace(rpath, "");
//            image_url = image_url.substring(1);
            image_url = image_url.replace(File.separator, "/");
            blog.setImage_url(image_url);

        } else if (blog.getImage_url() ==null||"".equals(blog.getImage_url())){
            blog.setImage_url(Constants.BLOG.DEFAULT_IMAGE_URL);
        }
        blog.setDate(DateUtil.getCurrentTimestamp());
        blog.setCreator_id(Constants.USER.ID);
        blog.setCreator_name(Constants.USER.NAME);
        blog.setCreate_time(DateUtil.getCurrentTimestamp());
        blog.setDelete_flag(0);
        blogDAO.add(blog);
    }


    public BlogDAO getBlogDAO() {
        return blogDAO;
    }

    public void setBlogDAO(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }
}
