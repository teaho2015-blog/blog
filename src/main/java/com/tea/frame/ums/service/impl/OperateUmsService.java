/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-5-26
 * Time: 下午9:06
 * To change this template use File | Settings | File Templates.
 */
package com.tea.frame.ums.service.impl;

import com.tea.frame.ums.dao.impl.UserFollowDao;
import com.tea.frame.ums.service.UmsService;

public class OperateUmsService implements UmsService {
    private UserFollowDao userFollowDao;

    public boolean checkedFollowed(String userId, String selfId) {
        return userFollowDao.findExist(userId, selfId);
    }

    public UserFollowDao getUserFollowDao() {
        return userFollowDao;
    }

    public void setUserFollowDao(UserFollowDao userFollowDao) {
        this.userFollowDao = userFollowDao;
    }
}
