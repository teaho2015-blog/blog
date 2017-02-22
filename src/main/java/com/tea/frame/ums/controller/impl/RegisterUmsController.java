/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-4-5
 * Time: 下午6:21
 * To change this template use File | Settings | File Templates.
 */
package com.tea.frame.ums.controller.impl;

import com.tea.frame.checker.Checker;
import com.tea.frame.ums.constant.UmsConstants;
import com.tea.frame.ums.controller.UmsController;
import com.tea.frame.ums.model.User;
import com.tea.frame.ums.service.impl.RegisterUmsService;
import com.tea.share.common.model.Person;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/register")
public class RegisterUmsController implements UmsController {
    /**
     * 日志实例
     */
    private static final Log logger = LogFactory.getLog(RegisterUmsController.class);
    private RegisterUmsService registerUmsService;
    private Checker finalUserSignupCheck;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public
    @ResponseBody
    Object viewFrontPage(HttpServletRequest request) {
        return new ModelAndView("forward:" + UmsConstants.SIGNIN_URL);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public
    @ResponseBody
    Object viewSigninPage() {
        return new ModelAndView("forward:" + UmsConstants.SIGNIN_URL);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public
    @ResponseBody
    Object viewSignupPage() {
        return new ModelAndView("forward:" + UmsConstants.SIGNUP_URL);
    }

    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public
    @ResponseBody
    Person getPerson(@PathVariable("id") int id) {
        logger.info("获取人员信息id=" + id);
        Person person = new Person();
        person.setName("张三");
        person.setSex("男");
        person.setAge(30);
        person.setId(id);
        return person;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public
    @ResponseBody
    Object signin(String email, String password, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if (logger.isDebugEnabled()) {
            logger.info("===============signin======================================");
            logger.info("获取人员信息 email = " + email);
            logger.info("获取人员信息 password = " + password);
        }
        User user = registerUmsService.signinUser(email, password, request);
//        logger.info(request.getAttribute("password2"));
        if (user == null) {
            mv.setViewName("redirect:/register/signin");
            try {
                mv.addObject("errorInfo", URLEncoder.encode(UmsConstants.SIGNIN_ERROR_INFO, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } else {
            mv.setViewName("redirect:/blog");
        }

        return mv;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public
    @ResponseBody
    Object signup(User user, HttpServletRequest request) {
        if (logger.isDebugEnabled()) {
            logger.info("===============signup=================================");
            logger.info("获取人员信息 email = " + user.getEmail());
            logger.info("获取人员信息 password = " + user.getPassword());
        }
        ModelAndView mv = new ModelAndView();
        if (finalUserSignupCheck.isValid(user)) {
            registerUmsService.signupUser(user, mv, request);
        } else {
            mv.setViewName("forward:" + UmsConstants.SIGNUP_URL);
        }

        return mv;
    }

    @RequestMapping(value = "/checkEmail", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public
    @ResponseBody
    String userExistCheck(@RequestParam(value = "email", required = false, defaultValue = "") String email) {
        logger.info("===============checkEmail=================================");
        logger.info("获取人员信息 email = " + email);
        return registerUmsService.checkExistByEmail(email).toString();
//        return "false";
    }


    public RegisterUmsService getRegisterUmsService() {
        return registerUmsService;
    }

    public void setRegisterUmsService(RegisterUmsService registerUmsService) {
        this.registerUmsService = registerUmsService;
    }

    public Checker getFinalUserSignupCheck() {
        return finalUserSignupCheck;
    }

    public void setFinalUserSignupCheck(Checker finalUserSignupCheck) {
        this.finalUserSignupCheck = finalUserSignupCheck;
    }
}
