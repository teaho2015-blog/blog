/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-4-9
 * Time: 上午3:18
 * To change this template use File | Settings | File Templates.
 */
package com.tea.frame.ums.model;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.tea.frame.valueObject.GenericFormat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class User implements RowMapper<User>,GenericFormat{
    private String id;
    private String username;
    private String password;
    private String role;
    private String email;
    private String telephone;
    private String ip;
    private String sessionid;
    private String sys_reserver1;
    private String sys_reserver2;
    private String sys_reserver3;
    private String sys_reserver4;
    private String sys_reserver5;
    private String creator_id;
    private String creator_name;
    private Date create_time;
    private String updator_id;
    private String updator_name;
    private Date update_time;
    private String deletor_id;
    private String deletor_name;
    private Date deletor_time;
    private String delete_flag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getSys_reserver1() {
        return sys_reserver1;
    }

    public void setSys_reserver1(String sys_reserver1) {
        this.sys_reserver1 = sys_reserver1;
    }

    public String getSys_reserver2() {
        return sys_reserver2;
    }

    public void setSys_reserver2(String sys_reserver2) {
        this.sys_reserver2 = sys_reserver2;
    }

    public String getSys_reserver3() {
        return sys_reserver3;
    }

    public void setSys_reserver3(String sys_reserver3) {
        this.sys_reserver3 = sys_reserver3;
    }

    public String getSys_reserver4() {
        return sys_reserver4;
    }

    public void setSys_reserver4(String sys_reserver4) {
        this.sys_reserver4 = sys_reserver4;
    }

    public String getSys_reserver5() {
        return sys_reserver5;
    }

    public void setSys_reserver5(String sys_reserver5) {
        this.sys_reserver5 = sys_reserver5;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getUpdator_id() {
        return updator_id;
    }

    public void setUpdator_id(String updator_id) {
        this.updator_id = updator_id;
    }

    public String getUpdator_name() {
        return updator_name;
    }

    public void setUpdator_name(String updator_name) {
        this.updator_name = updator_name;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getDeletor_id() {
        return deletor_id;
    }

    public void setDeletor_id(String deletor_id) {
        this.deletor_id = deletor_id;
    }

    public String getDeletor_name() {
        return deletor_name;
    }

    public void setDeletor_name(String deletor_name) {
        this.deletor_name = deletor_name;
    }

    public Date getDeletor_time() {
        return deletor_time;
    }

    public void setDeletor_time(Date deletor_time) {
        this.deletor_time = deletor_time;
    }

    public String getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(String delete_flag) {
        this.delete_flag = delete_flag;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        user.setEmail(rs.getString("email"));
        user.setTelephone(rs.getString("telephone"));
        user.setIp(rs.getString("ip"));
        user.setSessionid(rs.getString("sessionid"));
        user.setSys_reserver1(rs.getString("sys_reserver1"));
        user.setSys_reserver2(rs.getString("sys_reserver2"));
        user.setSys_reserver3(rs.getString("sys_reserver3"));
        user.setSys_reserver4(rs.getString("sys_reserver4"));
        user.setSys_reserver5(rs.getString("sys_reserver5"));
        user.setCreator_id(rs.getString("creator_id"));
        user.setCreator_name(rs.getString("creator_name"));
        user.setCreate_time(rs.getDate("create_time"));
        user.setUpdator_id(rs.getString("updator_id"));
        user.setUpdator_name(rs.getString("updator_name"));
        user.setUpdate_time(rs.getDate("update_time"));
        user.setDeletor_id(rs.getString("deletor_id"));
        user.setDeletor_name(rs.getString("deletor_name"));
        user.setDeletor_time(rs.getDate("delete_time"));
        user.setDelete_flag(rs.getString("delete_flag"));
        return user;
    }
}
