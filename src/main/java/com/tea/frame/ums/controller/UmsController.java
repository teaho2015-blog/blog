/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-3-29
 * Time: 下午12:15
 * To change this template use File | Settings | File Templates.
 */
package com.tea.frame.ums.controller;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller
public interface UmsController {
    Object viewFrontPage(HttpServletRequest request);

}