/**
 * Created with IntelliJ IDEA.
 * User: teaship
 * Date: 15-4-5
 * Time: 下午9:49
 * To change this template use File | Settings | File Templates.
 */
package net.teaho.blog.server.blog.vo;

import lombok.Data;

import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class BlogVO {
    @Id
    private String id;
    private String imageUrl;
    private Date date;
    private String title;
    private String titleSecondary;
//    private String pre_content;
    private String content;
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

    private String elderid;


}
