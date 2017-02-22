/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-4-28
 * Time: 下午11:51
 * To change this template use File | Settings | File Templates.
 */
package com.tea.frame.ums.dao.impl;

import com.tea.frame.ums.dao.UmsDao;
import com.tea.frame.ums.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Types;

public class SettingUmsDao implements UmsDao {
    private JdbcTemplate jdbcTemplate;

    public int updateUser(User user) {
        return this.jdbcTemplate.update(
                "UPDATE s_user set username=?,password=?,role=?,email=?,telephone=?,ip=?,sessionid=?,sys_reserver1=?, " +
                        " sys_reserver2=?,sys_reserver3=?,sys_reserver4=?,sys_reserver5=?,creator_id=?,creator_name=?,create_time=?, " +
                        " updator_id=?,updator_name=?,update_time=?,deletor_id=?,deletor_name=?,delete_time=?,delete_flag=? where id=? ",
                new Object[]{
                        user.getUsername(), user.getPassword(), user.getRole(), user.getEmail(), user.getTelephone(), user.getIp(),user.getSessionid(), user.getSys_reserver1(),
                        user.getSys_reserver2(), user.getSys_reserver3(), user.getSys_reserver4(), user.getSys_reserver5(), user.getCreator_id(), user.getCreator_name(), user.getCreate_time(),
                        user.getUpdator_id(), user.getUpdator_name(), user.getUpdate_time(), user.getDeletor_id(), user.getDeletor_name(), user.getDeletor_time(), user.getDelete_flag(),user.getId()
                }, new int[]{
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DATE,
                Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR,  Types.VARCHAR
        });

    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
