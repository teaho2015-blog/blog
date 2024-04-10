/**
 * Created with IntelliJ IDEA.
 * User: teaship
 * Date: 15-4-5
 * Time: 下午9:49
 * To change this template use File | Settings | File Templates.
 */
package com.tea.blog.dto;

import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Id;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;



public class BlogDTO implements RowMapper<BlogDTO>{
    @Id
    private String id;
    private String image_url;
    private Date date;
    private String title;
    private String title_secondary;
//    private String pre_content;
    private String content;
    private String creator_id;
    private String creator_name;
    private Timestamp create_time;
    private String updator_id;
    private String updator_name;
    private Timestamp update_time;
    private String deletor_id;
    private String deletor_name;
    private Timestamp delete_time;
    private int delete_flag;

    private String elderid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_secondary() {
        return title_secondary;
    }

    public void setTitle_secondary(String title_secondary) {
        this.title_secondary = title_secondary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(int delete_flag) {
        this.delete_flag = delete_flag;
    }

//    public String getPre_content() {
//        return pre_content;
//    }
//
//    public void setPre_content(String pre_content) {
//        this.pre_content = pre_content;
//    }


    public String getElderid() {
        return elderid;
    }

    public void setElderid(String elderid) {
        this.elderid = elderid;
    }

    @Override
    public BlogDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BlogDTO bb = new BlogDTO();
        bb.setId(rs.getString("id"));
        bb.setImage_url(rs.getString("image_url"));
        bb.setDate(rs.getDate("date"));
        bb.setTitle(rs.getString("title"));
        bb.setTitle_secondary(rs.getString("title_secondary"));
//        bb.setPre_content(rs.getString("pre_content"));
        bb.setContent(rs.getString("content"));
        bb.setCreator_id(rs.getString("creator_id"));
        bb.setCreator_name(rs.getString("creator_name"));
        bb.setCreate_time(rs.getTimestamp("create_time"));
        bb.setDate(rs.getDate("date"));
        bb.setUpdator_id(rs.getString("updator_id"));
        bb.setUpdator_name(rs.getString("updator_name"));
        bb.setUpdate_time(rs.getTimestamp("update_time"));
        bb.setDeletor_id(rs.getString("deletor_id"));
        bb.setDeletor_name(rs.getString("deletor_name"));
        bb.setDelete_time(rs.getTimestamp("delete_time"));
        bb.setDelete_flag(rs.getInt("delete_flag"));
        bb.setElderid(rs.getString("elderid"));
        return bb;
    }
}
