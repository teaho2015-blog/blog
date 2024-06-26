/**
 * @author teaho2015@gmail.com
 * since 2017/3/10
 */
package net.teaho.blog.server.blog.domain.builder;

import net.teaho.blog.server.blog.domain.Blog;
import net.teaho.blog.server.util.UUIDGenerator;

import java.sql.Timestamp;
import java.util.Date;


public class BlogBuilder {
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

    public static BlogBuilder newBlog() {
        return new BlogBuilder();
    }

    public BlogBuilder id(String id) {
        this.id = id;
        return this;
    }

    public BlogBuilder image_url(String image_url) {
        this.image_url = image_url;
        return this;
    }

    public BlogBuilder date(Date date) {
        this.date = date;
        return this;
    }

    public BlogBuilder title(String title) {
        this.title = title;
        return this;
    }

    public BlogBuilder title_secondary(String title_secondary) {
        this.title_secondary = title_secondary;
        return this;
    }

    public BlogBuilder content(String content) {
        this.content = content;
        return this;
    }

    public BlogBuilder creator_id(String creator_id) {
        this.creator_id = creator_id;
        return this;
    }

    public BlogBuilder creator_name(String creator_name) {
        this.creator_name = creator_name;
        return this;
    }

    public BlogBuilder create_time(Timestamp create_time) {
        this.create_time = create_time;
        return this;
    }

    public BlogBuilder updator_id(String updator_id) {
        this.updator_id = updator_id;
        return this;
    }

    public BlogBuilder updator_name(String updator_name) {
        this.updator_name = updator_name;
        return this;
    }

    public BlogBuilder update_time(Timestamp update_time) {
        this.update_time = update_time;
        return this;
    }

    public BlogBuilder deletor_id(String deletor_id) {
        this.deletor_id = deletor_id;
        return this;
    }

    public BlogBuilder deletor_name(String deletor_name) {
        this.deletor_name = deletor_name;
        return this;
    }

    public BlogBuilder delete_time(Timestamp delete_time) {
        this.delete_time = delete_time;
        return this;
    }

    public BlogBuilder delete_flag(int delete_flag) {
        this.delete_flag = delete_flag;
        return this;
    }

    public Blog build() {
        Blog blog = new Blog();
        blog.setId((id==null||"".equals(id))? UUIDGenerator.generateUUID() : id);
        blog.setImageUrl(image_url);
        blog.setDate(date);
        blog.setTitle(title);
        blog.setTitleSecondary(title_secondary);
        blog.setContent(content);
        blog.setCreatorId(creator_id);
        blog.setCreatorName(creator_name);
        blog.setCreateTime(create_time);
        blog.setUpdatorId(updator_id);
        blog.setUpdatorName(updator_name);
        blog.setUpdateTime(update_time);
        blog.setDeletorId(deletor_id);
        blog.setDeletorName(deletor_name);
        blog.setDeleteTime(delete_time);
        blog.setDeleteFlag(delete_flag);
        return blog;

    }


}
