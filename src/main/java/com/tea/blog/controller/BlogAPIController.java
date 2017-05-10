/**
 * @author teaho2015@gmail.com
 * since 2017/1/10
 */
package com.tea.blog.controller;


import com.tea.Constants;
import com.tea.blog.exception.NotFoundException;
import com.tea.blog.vo.BlogVO;
import com.tea.util.jdbc.support.Page;
import com.tea.blog.domain.Blog;
import com.tea.blog.service.BlogService;
import com.tea.blog.dto.PageDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/page/{id:^\\d+$}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object getPage(@PathVariable("id") int id) throws NotFoundException {
        Page page = blogService.getPage(id, Constants.BLOG.DEFAULT_PAGE_SIZE, true);
        PageDTO pageVO = new PageDTO();
        pageVO.setData(page.getResult());
        pageVO.setCurrentPageNum(page.getCurrentPageNo());
        pageVO.setPageSize(page.getPageSize());
        pageVO.setTotalPageCount(page.getTotalPageCount());
        return pageVO;
    }

    @RequestMapping(value = "/article/{id:^\\w+$}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object getArticle(HttpServletRequest request, @PathVariable("id") String id) throws NotFoundException {
        Blog blog = blogService.getArticle(id);
        return blog;
    }

    @RequestMapping(value = "/article/{id:^\\w+$}/attachElderId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object getArticleAttachElderId(HttpServletRequest request,@PathVariable("id") String id) throws NotFoundException {
        BlogVO blogVO = blogService.getArticleAttachElderId(id);
        return blogVO;
    }



    @RequestMapping(value = "/article/{id:^\\w+$}/elderOne", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object getElderArticle(HttpServletRequest request,@PathVariable("id") String id) throws NotFoundException {
        Blog blog = blogService.getElderArticle(id);
        return blog;
    }


    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleNotFoundException(HttpServletRequest request, NotFoundException e) {
//        request.removeAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE);
        return "{  \"message\": \"NotFoundException, "+ e.getMessage() +"\"," +
                "  \"documentation_url\": \"http://blog.teaho.net/api/v1\"" +
                "}";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.TEXT_PLAIN);
//        return new ResponseEntity<String>(ex.getMessage(), headers, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleNullPointException(HttpServletRequest request, NotFoundException e) {
        return "{  \"message\": \"NullPointerException, "+ e.getMessage() +"\"," +
                "  \"documentation_url\": \"http://blog.teaho.net/api/v1\"" +
                "}";

    }

    public BlogService getBlogService() {
        return blogService;
    }

    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }
}

