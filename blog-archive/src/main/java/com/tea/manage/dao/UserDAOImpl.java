/**
 * @author teaho2015@gmail.com
 * since 2016/10/20
 */
package com.tea.manage.dao;

import com.tea.manage.domain.DbUser;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl {
    private Logger logger = Logger.getLogger(getClass());

    public DbUser getDatabase(String username) {

        List<DbUser> users = internalDatabase();

        for (DbUser dbUser : users) {
            if (dbUser.getUsername().equals(username) == true) {
                logger.debug("User found");
                return dbUser;
            }
        }
        logger.error("User does not exist!");
        throw new RuntimeException("User does not exist!");

    }

    /**
     * 初始化数据
     */
    private List<DbUser> internalDatabase() {

        List<DbUser> users = new ArrayList<DbUser>();
        DbUser user = null;

        user = new DbUser();
        user.setUsername("admin");

        // "admin"经过MD5加密后
        user.setPassword("21232f297a57a5a743894a0e4a801fc3");
        user.setAccess(1);

        users.add(user);

        user = new DbUser();
        user.setUsername("user");

        // "user"经过MD5加密后
        user.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
        user.setAccess(2);

        users.add(user);

        return users;

    }
}
