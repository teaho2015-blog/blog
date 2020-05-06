/**
 * @author teaho2015@gmail.com
 * since 2016/12/1
 */
package com.tea.blog.service;

import com.tea.Constants;
import com.tea.api.url.NullMaterial;
import com.tea.api.url.UniversalPath;
import com.tea.blog.dao.BlogDAO;
import com.tea.blog.domain.Blog;
import com.tea.blog.vo.BlogVO;
import com.tea.common.exception.NotFoundException;
import com.tea.util.DateUtil;
import com.tea.util.UUIDGenerator;
import com.tea.util.jdbc.support.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.io.File.separator;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDAO blogDAO;

    public @Value("${app.pics.location}") String IMAGE_PATH;


    private static final Logger LOG = Logger.getLogger(BlogServiceImpl.class);


    @Override
    public Page<Blog> getPage(int pageNum) throws NotFoundException{
        return getPage(pageNum, Constants.BLOG.DEFAULT_PAGE_SIZE, false);
    }

    @Override
    public Page<Blog> getPage(int pageNum, int pageSize, boolean checkEmpty) throws NotFoundException {

        System.out.println(blogDAO.getTotalNum());

        Page<Blog> page = new Page<>(pageNum, blogDAO.getTotalNum(), pageSize,
                blogDAO.getBlogList(Page.getStartOfPage(pageNum,pageSize), pageSize ));
        if (checkEmpty && page.isEmpty()) {
            throw new NotFoundException("page is empty!");
        }
        return page;
    }

    @Override
    public Blog getArticle(String id) throws NotFoundException{
        Blog blog = blogDAO.get(id);
        if (blog == null || blog.getId() == null || blog.getId().isEmpty()) {
            throw new NotFoundException("article not found!");
        }
        return blog;
    }

    @Override
    public BlogVO getArticleAttachElderId(String id) throws NotFoundException{
        BlogVO blogVO = blogDAO.getAttachElderId(id);
        if (blogVO == null || blogVO.getId() == null || blogVO.getId().isEmpty()) {
            throw new NotFoundException("article not found!");
        }
        return blogVO;
    }

    @Override
    public Blog getElderArticle(String id) throws NotFoundException{
        Blog blog = blogDAO.getPreOne(id);
        if (blog == null || blog.getId() == null || blog.getId().isEmpty()) {
            throw new NotFoundException("article not found!");
        }
        return blog;
    }

    @Override
    public void deleteArticle(String id){
        blogDAO.deleteById(id);
    }

    @Override
    public String addImage(HttpServletRequest request, MultipartFile image) throws IOException {
        String link = "";
        if (image != null && image.getSize() != 0) {
//            String rpath = request.getSession().getServletContext()
//                    .getRealPath("");
            String orginalFileName = image.getOriginalFilename();
            StringBuffer dir = new StringBuffer();
            dir.append(IMAGE_PATH);
            String localDate = DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now());
            String fileDateDir = separator + localDate;
            dir.append(fileDateDir);

            //set file name
            String fileName =  UUIDGenerator.generateUUID() + orginalFileName.substring(orginalFileName.lastIndexOf("."),orginalFileName.length());

            String fileDestination = dir.toString() + separator + fileName;
            File f = new File(dir.toString());
            if (!f.exists()) {
                f.mkdirs();
            }
            FileCopyUtils.copy(image.getBytes(), new File(fileDestination));

            link = new UniversalPath(new UniversalPath(new UniversalPath(new NullMaterial(), "static/pics"), localDate), fileName).getName();
        }
        return link;
    }

    @Override
    public void createBlog(Blog blog) {
        blog.setId(UUIDGenerator.generateUUID());
        blog.setDate(DateUtil.getCurrentTimestamp());
        blog.setCreator_id(Constants.USER.ID);
        blog.setCreator_name(Constants.USER.NAME);
        blog.setCreate_time(DateUtil.getCurrentTimestamp());
        blog.setDelete_flag(0);
        blogDAO.add(blog);
    }

    @Override
    public void updateBlog(Blog blog) {
        blogDAO.updateById(blog);
    }


    public BlogDAO getBlogDAO() {
        return blogDAO;
    }

    public void setBlogDAO(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }
}
