/**
 * @author teaho2015@gmail.com
 * since 2016/12/1
 */
package com.tea.blog.service;

import com.tea.blog.exception.NotFoundException;
import com.tea.blog.vo.BlogVO;
import com.tea.frame.jdbc.support.Page;
import com.tea.Constants;
import com.tea.blog.dao.BlogDAO;
import com.tea.blog.domain.Blog;
import com.tea.blog.dto.BlogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDAO blogDAO;

    @Override
    public Page getPage(int pageNum) throws NotFoundException{
        return getPage(pageNum, Constants.BLOG.DEFAULT_PAGE_SIZE, false);
    }

    @Override
    public Page getPage(int pageNum, int pageSize, boolean checkEmpty) throws NotFoundException {
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

    public BlogDAO getBlogDAO() {
        return blogDAO;
    }

    public void setBlogDAO(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }
}
