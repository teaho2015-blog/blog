/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-4-9
 * Time: 下午9:48
 * To change this template use File | Settings | File Templates.
 */
package com.tea.frame.ums.dao.impl;

import com.tea.frame.ums.dao.UmsDao;
import com.tea.frame.ums.model.User;
import com.tea.frame.util.StructureUtil;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;
import java.util.Map;

public class RegisterUmsDao implements UmsDao {
    private static final Logger logger = Logger.getLogger(RegisterUmsDao.class);
    private JdbcTemplate jdbcTemplate;

    public int addUser(final User user) {
        return this.jdbcTemplate.update(
                "Insert Into s_user(id,username,password,role,email,telephone,ip,sessionid,sys_reserver1, " +
                        " sys_reserver2,sys_reserver3,sys_reserver4,sys_reserver5,creator_id,creator_name,create_time, " +
                        " updator_id,updator_name,update_time,deletor_id,deletor_name,delete_time,delete_flag) value ( " +
                        " ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
                new Object[]{
                        user.getId(), user.getUsername(), user.getPassword(), user.getRole(), user.getEmail(), user.getTelephone(), user.getIp(),user.getSessionid(), user.getSys_reserver1(),
                        user.getSys_reserver2(), user.getSys_reserver3(), user.getSys_reserver4(), user.getSys_reserver5(), user.getCreator_id(), user.getCreator_name(), user.getCreate_time(),
                        user.getUpdator_id(), user.getUpdator_name(), user.getUpdate_time(), user.getDeletor_id(), user.getDeletor_name(), user.getDeletor_time(), user.getDelete_flag()
                }, new int[]{
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DATE,
                Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR
        });
    }



    public User getUserById(String id) {
       User user;
       try{
           user = this.jdbcTemplate.queryForObject(
                   "select * from s_user where id = ? and delete_flag='0'",
                   new Object[]{id},
                   new User());
       }catch (Exception e) {
           e.printStackTrace();
           return null;
       }
        return user;
    }

    public User findUser(String retrieveHolder, String retrieveParam) {

//        List<Map<String,Object>> users = this.jdbcTemplate.queryForList(
//                "select *  from s_user where " + retrieveHolder + " = ?",
//                new Object[]{retrieveParam},
//                new User());
//        if (users == null){
//            return null;
//        }else {
//            User user = new User();
//            user = (User)StructureUtil.MapToObject(users.get(0),user);
//            return user;
//
//        }
        User user;
        try {
            user = this.jdbcTemplate.queryForObject(
                "select *  from s_user where " + retrieveHolder + " = ? and delete_flag='0'",
                new Object[]{retrieveParam},
                new User());
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }


        return user;



    }

    public Boolean checkUserExist(String retrieveHolder, String retrieveParam) {
        User user = findUser(retrieveHolder, retrieveParam);
        if (user == null) {
            return false;
        }else {
            return true;
        }
    }

    public void updateUser(User user) {
        this.jdbcTemplate.update(
                "update s_user set username=?,password=?,role=?,email=?,telephone=?,ip=?,sessionid=?,sys_reserver1=?, " +
                        " sys_reserver2=?,sys_reserver3=?,sys_reserver4=?,sys_reserver5=?,creator_id=?,creator_name=?,create_time=?, " +
                        " updator_id=?,updator_name=?,update_time=?,deletor_id=?,deletor_name=?,delete_time=?,delete_flag=? where id=?",
                new Object[]{
                        user.getUsername(), user.getPassword(), user.getRole(), user.getEmail(), user.getTelephone(), user.getIp(),user.getSessionid(), user.getSys_reserver1(),
                        user.getSys_reserver2(), user.getSys_reserver3(), user.getSys_reserver4(), user.getSys_reserver5(), user.getCreator_id(), user.getCreator_name(), user.getCreate_time(),
                        user.getUpdator_id(), user.getUpdator_name(), user.getUpdate_time(), user.getDeletor_id(), user.getDeletor_name(), user.getDeletor_time(), user.getDelete_flag(),user.getId()
                }, new int[]{
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR
        });
    }

    public List<User> queryUsers() {
        List<User> blogBasics = this.jdbcTemplate.query(
                " select * from s_user where delete_flag='0' ",
                new Object[]{},
                new User()
        );
        return blogBasics;
    }

    public void delUser(Object[] arg) {
        this.jdbcTemplate.update(
                    "UPDATE S_USER SET deletor_id=?, deletor_name=?, delete_time=?, delete_flag='1' WHERE id=? ",
                new Object[]{
                    arg[0],arg[1],arg[2],arg[3]
                }, new int[]{
                    Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR
        });
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
