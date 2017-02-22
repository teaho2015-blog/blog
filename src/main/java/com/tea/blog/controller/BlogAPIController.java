/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-3-27
 * Time: 下午9:55
 * To change this template use File | Settings | File Templates.
 */
package com.tea.blog.controller;


import com.tea.frame.jdbc.support.Page;
import com.tea.blog.domain.Blog;
import com.tea.blog.service.BlogService;
import com.tea.blog.vo.BlogVO;
import com.tea.blog.vo.PageVO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/v1/blog")
public class BlogAPIController {

    /** 日志实例 */
    private  final Logger logger = Logger.getLogger(getClass());

    private final static String AllBLOG_URL = "/page/blog/all_blog.jsp";
    private final static String ABOUT_URL = "/page/blog/about.jsp";
    private final static int DEFAULT_PAGE_NUM = 1;

    private BlogService blogService;

    @RequestMapping(value = "/page/{id:^\\w+$}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object getPage(@PathVariable("id") int id) {
        Page page = blogService.getPage(id);
        PageVO pageVO = new PageVO();
        pageVO.setData(page.getResult());
        pageVO.setCurrentPageNum(page.getCurrentPageNo());
        pageVO.setPageSize(page.getPageSize());
        pageVO.setTotalPageCount(page.getTotalPageCount());
        return pageVO;
    }

    @RequestMapping(value = "/article/{id:^\\w+$}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object getArticle(HttpServletRequest request,@PathVariable("id") String id) {
        Blog blog = blogService.getArticle(id);
        return blog;
    }

    @RequestMapping(value = "/article/{id:^\\w+$}/attachElderId", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object getArticleAttachElderId(HttpServletRequest request,@PathVariable("id") String id) {
        BlogVO blogVO = blogService.getArticleAttachElderId(id);
        return blogVO;
    }



    @RequestMapping(value = "/article/{id:^\\w+$}/elderOne", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object getElderArticle(HttpServletRequest request,@PathVariable("id") String id) {
        Blog blog = blogService.getElderArticle(id);
        return blog;
    }

    public BlogService getBlogService() {
        return blogService;
    }

    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }
}

