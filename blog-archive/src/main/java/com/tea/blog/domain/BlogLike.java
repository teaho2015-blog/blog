/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-4-24
 * Time: 下午8:26
 * To change this template use File | Settings | File Templates.
 */
package com.tea.blog.domain;

import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Table;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * table name:b_blog_like
 */
@Table(name = "b_blog_like")
public class BlogLike  implements RowMapper<BlogLike> {
    private String id;
    private String blog_id;
    private Timestamp like_date;
    private String user_id;
    private String creator_id;
    private String creator_name;
    private Timestamp create_time;
    private String updator_id;
    private String updator_name;
    private Timestamp update_time;
    private String deletor_id;
    private String deletor_name;
    private Timestamp delete_time;
    private String delete_flag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(String blog_id) {
        this.blog_id = blog_id;
    }

    public Timestamp getLike_date() {
        return like_date;
    }

    public void setLike_date(Timestamp like_date) {
        this.like_date = like_date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
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

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
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

    public Timestamp getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(Timestamp delete_time) {
        this.delete_time = delete_time;
    }

    public String getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(String delete_flag) {
        this.delete_flag = delete_flag;
    }

    @Override
    public BlogLike mapRow(ResultSet rs, int rowNum) throws SQLException {
        BlogLike bl = new BlogLike();
        bl.setId(rs.getString("id"));
        bl.setBlog_id(rs.getString("blog_id"));
        bl.setLike_date(rs.getTimestamp("like_date"));
        bl.setCreator_id(rs.getString("creator_id"));
        bl.setUser_id(rs.getString("user_id"));
        bl.setCreator_name(rs.getString("creator_name"));
        bl.setCreate_time(rs.getTimestamp("create_time"));
        bl.setUpdator_id(rs.getString("updator_id"));
        bl.setUpdator_name(rs.getString("updator_name"));
        bl.setUpdate_time(rs.getTimestamp("update_time"));
        bl.setDeletor_id(rs.getString("deletor_id"));
        bl.setDeletor_name(rs.getString("deletor_name"));
        bl.setDelete_time(rs.getTimestamp("delete_time"));
        bl.setDelete_flag(rs.getString("delete_flag"));
        return bl;
    }
}
