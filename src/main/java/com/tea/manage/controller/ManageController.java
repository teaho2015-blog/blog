/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-3-27
 * Time: 下午9:55
 * To change this template use File | Settings | File Templates.
 */
package com.tea.manage.controller;


import com.tea.blog.domain.Blog;
import com.tea.manage.service.ManageService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
@RequestMapping("/manage")
public class ManageController {

    /** 日志实例 */
    private  final Logger logger = Logger.getLogger(getClass());

    private static String LOGIN_URL = "/page/manage/login.jsp";
    private static String DENIED_URL = "/page/manage/loginDenied.jsp";
    private static String BLOG_EDITOR_URL = "/page/manage/blog_editor.jsp";

    private ManageService manageService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object viewLoginPage() {
        return new ModelAndView("forward:"+ LOGIN_URL);
    }

  /*  @RequestMapping(value = "/login", method = RequestMethod.POST)
    public
    @ResponseBody
    Object signin(User user, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if (logger.isDebugEnabled()) {
            logger.info("===============signin======================================");
            logger.info("获取人员信息 email = " + user.getUsername());
            logger.info("获取人员信息 password = " + user.getPassword());
        }
        if (user == null) {
            mv.setViewName("redirect:/register/signin");
        } else {
            mv.setViewName("redirect:/blog");
        }

        return mv;
    }*/


    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object viewDeniedPage() {
        return new ModelAndView("forward:"+ DENIED_URL);
    }

    @RequestMapping(value = "/blog_list", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object viewBlogListPage() {

        return new ModelAndView("forward:"+ BLOG_EDITOR_URL);
    }


    @RequestMapping(value = "/blog_editor", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object viewBlogEditorPage() {
        return new ModelAndView("forward:"+ BLOG_EDITOR_URL);
    }

    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object postBlog(Blog blog,@RequestParam("image") MultipartFile image, HttpServletRequest request) throws IOException {
        manageService.newBlog(blog, image, request);
        return "{state : \"success\"}";
    }

    public ManageService getManageService() {
        return manageService;
    }

    public void setManageService(ManageService manageService) {
        this.manageService = manageService;
    }
}

