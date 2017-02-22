/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-4-9
 * Time: 下午9:31
 * To change this template use File | Settings | File Templates.
 */
package com.tea.frame.ums.service.impl;

import com.tea.frame.util.DateUtil;
import com.tea.frame.util.RequestUtil;
import com.tea.frame.util.UUIDGenerator;
import com.tea.frame.util.UserUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.tea.frame.ums.dao.impl.RegisterUmsDao;
import com.tea.frame.ums.service.UmsService;
import com.tea.frame.ums.model.User;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


public class RegisterUmsService implements UmsService {
    /** 日志实例 */
    private static final Log logger = LogFactory.getLog(RegisterUmsService.class);
    private RegisterUmsDao registerUmsDao;

    public User signinUser(String email,String password,HttpServletRequest request) {
        User user = registerUmsDao.findUser("email",email);
        if (user == null){
//            user = new User();
//            user.setId(UUIDGenerator.generateUUID());
//            user.setEmail(email);
//            user.setPassword(password);
//            user.setIp(RequestUtil.getRemoteAddr(request));
//            user.setUsername(email);
//            user.setCreate_time(DateUtil.getCurrentTimestamp());
//            user.setDelete_flag("0");
//            user.setSessionid(request.getSession().getId());
//            registerUmsDao.addUser(user);

        }else if (user.getPassword().equals(password)){
            user.setSessionid(request.getSession().getId());
            user.setUpdator_id(user.getId());
            user.setUpdator_name(user.getUsername());
            user.setUpdate_time(DateUtil.getCurrentTimestamp());
            registerUmsDao.updateUser(user);
        }else {
            user = null;
        }
        request.getSession().setAttribute(User.class.getName(), user);
        return user;

    }

    public ModelAndView signupUser(User user,ModelAndView mv, HttpServletRequest request) {
        User userFromDB = registerUmsDao.findUser("email",user.getEmail());
        if (userFromDB == null) {
            user.setId(UUIDGenerator.generateUUID());
            user.setCreator_id(user.getId());
            user.setCreator_name(user.getUsername());
            user.setCreate_time(DateUtil.getCurrentTimestamp());
            user.setDelete_flag("0");
            if (registerUmsDao.addUser(user)>0) {
                request.getSession().setAttribute(User.class.getName(),user);
                mv.setViewName("redirect:/register/signin");
            }
        }
        if (StringUtils.isEmpty(mv.getViewName())) {
            mv.setViewName("redirect:/register/signup");
        }
        return mv;
    }

    public User getUser(String id) {
        return registerUmsDao.getUserById(id);

    }

    public Boolean checkExistByEmail(String email) {
        return registerUmsDao.checkUserExist("email", email);
    }


    public RegisterUmsDao getRegisterUmsDao() {
        return registerUmsDao;
    }

    public void setRegisterUmsDao(RegisterUmsDao registerUmsDao) {
        this.registerUmsDao = registerUmsDao;
    }
}
