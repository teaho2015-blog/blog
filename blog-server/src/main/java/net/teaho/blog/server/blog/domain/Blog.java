/**
 * Created with IntelliJ IDEA.
 * User: teaship
 * Date: 15-4-5
 * Time: 下午9:49
 * To change this template use File | Settings | File Templates.
 */
package net.teaho.blog.server.blog.domain;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


/**
 * table name:b_blog
 */
@Table(name = "b_blog")
@Data
public class Blog {
    @Id
    private String id;
    private String imageUrl;
    private Date date;
    private String title;
    private String titleSecondary;
//    private String pre_content;
    private String content;
    private Integer type;
    private String externalUrl;
    private String creatorId;
    private String creatorName;
    private Timestamp createTime;
    private String updatorId;
    private String updatorName;
    private Timestamp updateTime;
    private String deletorId;
    private String deletorName;
    private Timestamp deleteTime;
    private int deleteFlag;

}
