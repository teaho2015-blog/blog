/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-5-26
 * Time: 下午11:28
 * To change this template use File | Settings | File Templates.
 */
package com.tea.frame.ums.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowVO implements RowMapper {
    private String id;
    private String username;
    private String describtion;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    @Override
    public FollowVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        FollowVO user = new FollowVO();
        user.setId(rs.getString("id"));
        user.setUsername(rs.getString("username"));
        user.setDescribtion(rs.getString("describtion"));
        return user;
    }
}
