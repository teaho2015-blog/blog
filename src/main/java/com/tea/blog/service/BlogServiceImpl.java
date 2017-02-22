/**
 * @author teaho2015@gmail.com
 * since 2016/12/1
 */
package com.tea.blog.service;

import com.tea.frame.jdbc.support.Page;
import com.tea.Constants;
import com.tea.blog.dao.BlogDAO;
import com.tea.blog.domain.Blog;
import com.tea.blog.vo.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDAO blogDAO;

    @Override
    public Page getPage(int pageNum) {
        return getPage(pageNum, Constants.BLOG.DEFAULT_PAGE_SIZE);
    }

    @Override
    public Page getPage(int pageNum, int pageSize) {
        Page page = new Page(pageNum, blogDAO.getTotalNum(), pageSize,
                blogDAO.getBlogList(Page.getStartOfPage(pageNum,pageSize), pageSize ));
        return page;
    }

    @Override
    public Blog getArticle(String id) {
        return blogDAO.get(id);
    }

    @Override
    public BlogVO getArticleAttachElderId(String id) {
        return blogDAO.getAttachElderId(id);
    }

    @Override
    public Blog getElderArticle(String id) {
        return blogDAO.getPreOne(id);
    }
}
