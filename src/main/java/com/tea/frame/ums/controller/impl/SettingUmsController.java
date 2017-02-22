/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-4-5
 * Time: 下午6:21
 * To change this template use File | Settings | File Templates.
 */
package com.tea.frame.ums.controller.impl;

import com.tea.frame.ums.constant.UmsConstants;
import com.tea.frame.ums.controller.UmsController;
import com.tea.frame.ums.model.User;
import com.tea.frame.ums.service.impl.RegisterUmsService;
import com.tea.frame.ums.service.impl.SettingUmsService;
import com.tea.frame.util.UserUtil;
import com.tea.share.common.model.Person;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/setting")
public class SettingUmsController implements UmsController{
    /** 日志实例 */
    private static final Log logger = LogFactory.getLog(SettingUmsController.class);

    private SettingUmsService settingUmsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    Object viewFrontPage(HttpServletRequest request) {
        logger.info("setting页面跳转");
        User user = UserUtil.getCurrentLoginUser(request);
        ModelAndView mv = new ModelAndView("forward:"+ UmsConstants.SETTING_URL);
        mv.addObject("user",user);
        return mv;
    }




    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody
    Object setOptions(String telephone, HttpServletRequest request) {
        if (logger.isDebugEnabled()) {
            logger.debug("setting页面设置");
        }
        ModelAndView mv = new ModelAndView();
        User user = settingUmsService.setUserSetting(telephone,request);
        mv.setViewName("forward:" + UmsConstants.SETTING_URL);
        mv.addObject("user", user);
        return mv;
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public @ResponseBody
    Object removeUser(HttpServletRequest request) {
        if (logger.isDebugEnabled()) {
            logger.debug("session设置");
        }
//        ModelAndView modelAndView = new ModelAndView();
        UserUtil.removeCurrentLoginUser(request);
//        modelAndView.setViewName("/register");
//        return modelAndView;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","删除成功");
        return jsonObject;
    }


    public SettingUmsService getSettingUmsService() {
        return settingUmsService;
    }

    public void setSettingUmsService(SettingUmsService settingUmsService) {
        this.settingUmsService = settingUmsService;
    }
}
