/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-5-26
 * Time: 下午9:08
 * To change this template use File | Settings | File Templates.
 */
package com.tea.frame.ums.dao.impl;

import com.tea.frame.ums.dao.UmsDao;
import com.tea.frame.ums.model.FollowVO;
import com.tea.frame.ums.model.User;
import com.tea.frame.ums.model.UserFollow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class UserFollowDao implements UmsDao {
    private JdbcTemplate jdbcTemplate;

    public boolean findExist(String userId, String selfId) {
        UserFollow userFollow;
        try {
            userFollow = this.jdbcTemplate.queryForObject(
                    " select *  from s_user_follow where followed_userid=? and creator_id=? and delete_flag='0' ",
                    new Object[]{userId, selfId},
                    new UserFollow());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return userFollow != null;
    }



    public List getFollowInfoList(String creator_id) {
        String sql = "SELECT u.id as id,u.username as username,u.sys_reserver1 as describtion FROM S_USER u,S_USER_FOLLOW f where u.id=u.creator_id and u.delete_flag='0' and f.delete_flag='0'";
        List followList = this.jdbcTemplate.query(
                            sql,
                            new FollowVO()
                        );
        return followList;
    }

    public UserFollow findFollow(String id) {
        UserFollow userFollow;
        try {
            userFollow = this.jdbcTemplate.queryForObject(
                    "select *  from s_user_follow where  id = ? and delete_flag=0",
                    new Object[]{id},
                    new UserFollow());
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userFollow;
    }

    public void delFollow(Object[] args) {
        this.jdbcTemplate.update(
                "UPDATE S_USER_FOLLOW SET deletor_id=?, deletor_name=?,delete_time=?,delete_flag='1' WHERE id=? ",
                new Object[]{
                        args[0],args[1],args[2],args[3]
                }, new int[]{
                Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR
        });
    }

    public int addFollow(UserFollow userFollow) {
       return this.jdbcTemplate.update(
               "Insert Into s_user_follow(id,followed_userid,creator_id,creator_name,create_time, " +
                       " updator_id,updator_name,update_time,deletor_id,deletor_name,delete_time,delete_flag) value ( " +
                       " ?,?,?,?,?,?,?,?,?,?,?,?) ",
               new Object[]{
                       userFollow.getId(), userFollow.getFollowed_userid(), userFollow.getCreator_id(), userFollow.getCreator_name(), userFollow.getCreate_time(),
                       userFollow.getUpdator_id(), userFollow.getUpdator_name(), userFollow.getUpdate_time(), userFollow.getDeletor_id(), userFollow.getDeletor_name(), userFollow.getDeletor_time(), userFollow.getDelete_flag()
               }, new int[]{
               Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DATE,
               Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR
       });
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
