/**
 * Created with IntelliJ IDEA.
 * User: teaship
 * Date: 15-4-5
 * Time: 下午9:49
 * To change this template use File | Settings | File Templates.
 */
package net.teaho.blog.server.api.dto;

import net.teaho.blog.server.common.Builder;
import net.teaho.blog.server.api.url.*;
import net.teaho.blog.server.blog.vo.BlogVO;

import java.sql.Timestamp;
import java.util.Date;


public class BlogAttachIdDTO {
    private String id;
    private String image_url;
    private Date date;
    private String title;
    private String title_secondary;
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
    private String html_url;
    private String article_url;

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

    public String getElderid() {
        return elderid;
    }

    public void setElderid(String elderid) {
        this.elderid = elderid;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public static InnerBuilder newBuilder() {
        return new InnerBuilder();
    }

    public static class InnerBuilder implements Builder<BlogAttachIdDTO> {
        private BlogAttachIdDTO blogAttachIdDTO;

        public InnerBuilder() {
            blogAttachIdDTO = new BlogAttachIdDTO();
        }

        public InnerBuilder id(String n) {
            blogAttachIdDTO.id = n;
            return this;
        }

        public InnerBuilder image_url(String n) {
            blogAttachIdDTO.image_url = n;
            return this;
        }

        public InnerBuilder date(Date d) {
            blogAttachIdDTO.date = d == null ? null : (Date) d.clone();
            return this;
        }

        public InnerBuilder title(String str) {
            blogAttachIdDTO.title = str;
            return this;
        }

        public InnerBuilder title_secondary(String str) {
            blogAttachIdDTO.title_secondary = str;
            return this;
        }

        public InnerBuilder content(String str) {
            blogAttachIdDTO.content = str;
            return this;
        }

        public InnerBuilder creator_id(String str) {
            blogAttachIdDTO.creator_id = str;
            return this;
        }

        public InnerBuilder creator_name(String str) {
            blogAttachIdDTO.creator_name = str;
            return this;
        }

        public InnerBuilder create_time(Timestamp timestamp) {
            blogAttachIdDTO.create_time = timestamp;
            return this;
        }

        public InnerBuilder updator_id(String str) {
            blogAttachIdDTO.updator_id = str;
            return this;
        }

        public InnerBuilder updator_name(String str) {
            blogAttachIdDTO.updator_name = str;
            return this;
        }

        public InnerBuilder update_time(Timestamp str) {
            blogAttachIdDTO.update_time = str;
            return this;
        }

        public InnerBuilder deletor_id(String str) {
            blogAttachIdDTO.deletor_id = str;
            return this;
        }

        public InnerBuilder deletor_name(String str) {
            blogAttachIdDTO.deletor_name = str;
            return this;
        }

        public InnerBuilder delete_time(Timestamp str) {
            blogAttachIdDTO.delete_time = str;
            return this;
        }

        public InnerBuilder delete_flag(int i) {
            blogAttachIdDTO.delete_flag = i;
            return this;
        }

        public InnerBuilder elderid(String i) {
            blogAttachIdDTO.elderid = i;
            return this;
        }

        public InnerBuilder html_url(String i) {
            blogAttachIdDTO.html_url = i;
            return this;
        }

        public InnerBuilder article_url(String i) {
            blogAttachIdDTO.article_url = i;
            return this;
        }

        public InnerBuilder parse(BlogVO blogVO) {
            id(blogVO.getId())
                    .image_url(blogVO.getImage_url())
                    .date(blogVO.getDate() == null ? null : (Date) blogVO.getDate().clone())
                    .content(blogVO.getContent())
                    .title(blogVO.getTitle())
                    .title_secondary(blogVO.getTitle_secondary())
                    .creator_id(blogVO.getCreator_id())
                    .creator_name(blogVO.getCreator_name())
                    .create_time(blogVO.getCreate_time())
                    .updator_id(blogVO.getUpdator_id())
                    .updator_name(blogVO.getUpdator_name())
                    .update_time(blogVO.getUpdate_time())
                    .deletor_id(blogVO.getDeletor_id())
                    .deletor_name(blogVO.getDeletor_name())
                    .delete_time(blogVO.getDelete_time())
                    .delete_flag(blogVO.getDelete_flag())
                    .elderid(blogVO.getElderid())
                    .html_url(new UniversalPath(new ArticlePath(new Domain()), blogVO.getId()).getName())
                    .article_url(new UniversalPath(new ArticlePath(new BlogPath(new APIPath(new Domain()))), blogVO.getId()).getName());
            return this;
        }

        @Override
        public BlogAttachIdDTO build() {
            return blogAttachIdDTO;
        }
    }

}
