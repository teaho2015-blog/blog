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
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/")
public class BlogController {

    /** 日志实例 */
    private  final Logger logger = Logger.getLogger(getClass());

    private final static String BLOG_HOME_URL = "/page/blog/blog_home.jsp";
    private final static String ARTICLE_URL = "/page/blog/article.jsp";
    private final static String ABOUT_URL = "/page/blog/about.jsp";
    private final static int DEFAULT_PAGE_NUM = 1;

    private BlogService blogService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object viewBlog(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:" +  BLOG_HOME_URL);
        Page page = blogService.getPage(DEFAULT_PAGE_NUM);

        mv.addObject("blogList", page.getResult());
//        mv.addObject("blogTotalNum", page.getTotalCount());
        mv.addObject("blogTotalPageNum", page.getTotalPageCount());
        mv.addObject("currentPageNum", page.getCurrentPageNo());
        mv.addObject("pageSize", page.getPageSize());

        return mv;
    }

//    @RequestMapping(value = "", method = RequestMethod.POST)
//    @ResponseStatus(code = HttpStatus.OK)
//    public @ResponseBody
//    Object postBlogPage(HttpServletRequest request) {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("forward:" +  BLOG_HOME_URL);
//        return mv;
//    }

    @RequestMapping(value = "/page/{pageNum:\\d+}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object viewBlogPage(HttpServletRequest request,@PathVariable("pageNum") int pageNum) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:" +  BLOG_HOME_URL);
        Page page = blogService.getPage(pageNum);

        mv.addObject("blogList", page.getResult());
//        mv.addObject("blogTotalNum", page.getTotalCount());
        mv.addObject("blogTotalPageNum", page.getTotalPageCount());
        mv.addObject("currentPageNum", page.getCurrentPageNo());
        mv.addObject("pageSize", page.getPageSize());

        return mv;
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object viewAboutPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:" + ABOUT_URL);
        return mv;
    }


     @RequestMapping(value = "/article/{id:^\\w+$}", method = RequestMethod.GET)
     @ResponseStatus(code = HttpStatus.OK)
     public @ResponseBody
     Object viewArticlePage(HttpServletRequest request,@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:" +  ARTICLE_URL + "?id=" + id);
//        Blog blog = blogService.getArticle(id);
//         mv.addObject("article", blog);
        return mv;
    }



    @RequestMapping(value = "/article/{id:^\\w+$}/elderOne", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object getNextArticle(HttpServletRequest request,@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:" +  BLOG_HOME_URL);
        Blog blog = blogService.getElderArticle(id);
        mv.addObject("article", blog);
        return mv;
    }

    public BlogService getBlogService() {
        return blogService;
    }

    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }
}

