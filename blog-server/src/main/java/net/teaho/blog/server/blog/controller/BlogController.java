/**
 * @author teaho2015@gmail.com
 * since 2017/1/10
 */
package net.teaho.blog.server.blog.controller;


import net.teaho.blog.server.blog.service.BlogService;
import net.teaho.blog.server.common.exception.NotFoundException;
import net.teaho.blog.server.util.jdbc.support.Page;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/")
public class BlogController {

    /** 日志实例 */
    protected final Logger logger = Logger.getLogger(getClass());

    protected final static String BLOG_HOME_URL = "/page/blog/blog_home.jsp";
    protected final static String ARTICLE_URL = "/page/blog/article.jsp";
    protected final static String ABOUT_URL = "/page/blog/about.jsp";
    protected final static int DEFAULT_PAGE_NUM = 1;

    protected static final String MODEL_NAME_BLOGLIST = "blogList";
    protected static final String MODEL_NAME_BLOGTOTALPAGENUM = "blogTotalPageNum";
    protected static final String MODEL_NAME_CURRENTPAGENUM = "currentPageNum";
    protected static final String MODEL_NAME_PAGESIZE = "pageSize";

    private BlogService blogService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object viewBlog(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:" +  BLOG_HOME_URL);

        Page page = null;
        try {
            page = blogService.getPage(DEFAULT_PAGE_NUM);
            mv.addObject(MODEL_NAME_BLOGLIST, page.getResult());
            mv.addObject(MODEL_NAME_BLOGTOTALPAGENUM, page.getTotalPageCount());
            mv.addObject(MODEL_NAME_CURRENTPAGENUM, page.getCurrentPageNo());
            mv.addObject(MODEL_NAME_PAGESIZE, page.getPageSize());
        } catch (NotFoundException e) {
            logger.info("page data is empty, but nullable page is allow for home page!!", e);
        }

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
    Object viewBlogPage(HttpServletRequest request,@PathVariable("pageNum") int pageNum) throws NotFoundException {
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


    public BlogService getBlogService() {
        return blogService;
    }

    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }
}

