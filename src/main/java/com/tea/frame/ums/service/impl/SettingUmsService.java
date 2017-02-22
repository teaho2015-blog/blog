/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-4-28
 * Time: 下午11:50
 * To change this template use File | Settings | File Templates.
 */
package com.tea.frame.ums.service.impl;

import com.tea.frame.ums.dao.impl.SettingUmsDao;
import com.tea.frame.ums.model.User;
import com.tea.frame.ums.service.UmsService;
import com.tea.frame.util.UserUtil;

import javax.servlet.http.HttpServletRequest;

public class SettingUmsService implements UmsService {
    private SettingUmsDao settingUmsDao;


    public User setUserSetting( String telephone, HttpServletRequest request) {
        User user = UserUtil.getCurrentLoginUser(request);
//        user.setUsername(username);
        user.setTelephone(telephone);
        if (settingUmsDao.updateUser(user)>0) {
            request.getSession().setAttribute(User.class.getName(),user);
        }
        return user;

    }

    public SettingUmsDao getSettingUmsDao() {
        return settingUmsDao;
    }

    public void setSettingUmsDao(SettingUmsDao settingUmsDao) {
        this.settingUmsDao = settingUmsDao;
    }
}
