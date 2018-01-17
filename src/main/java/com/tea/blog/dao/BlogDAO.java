/**
 * Created with IntelliJ IDEA.
 * User: tea
 * Date: 16-11-02
 * Time: 下午8:57
 */
package com.tea.blog.dao;

import com.tea.blog.domain.Blog;
import com.tea.blog.dto.BlogDTO;
import com.tea.blog.vo.BlogVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("blogDao")
public interface BlogDAO {
    @Insert("INSERT INTO B_BLOG (ID, IMAGE_URL, DATE, TITLE, TITLE_SECONDARY, CONTENT, CREATOR_ID, CREATOR_NAME, CREATE_TIME, DELETE_FLAG) VALUES (#{id}, #{image_url}, #{date}, #{title}, #{title_secondary}, #{content}, #{creator_id}, #{creator_name}, #{create_time}, #{delete_flag})")
    @Options(useGeneratedKeys = true, keyProperty = "ID")
    public void add(Blog blog);

    @Select("SELECT COUNT(*) FROM B_BLOG WHERE DELETE_FLAG = '0' ")
    public int getTotalNum();


    @Select("SELECT * FROM B_BLOG WHERE DELETE_FLAG=0 ORDER BY CREATE_TIME DESC LIMIT #{0},#{1}")
    public List<Blog> getBlogList(long preIndex, int range);



    @Select("SELECT * FROM B_BLOG A WHERE ID=#{0} WHERE DELETE_FLAG=0 ")
    public Blog get(String id);
    
/**
 *
 * below sql which gen rownum first is a hard way to get pre blog
 *    SELECT @rownum:=@rownum+1 rownum, id, CREATE_TIME From
 *    (SELECT @rownum:=0,b_blog.* FROM b_blog WHERE id!=''  ORDER BY CREATE_TIME limit 10) t
 */
    @Select("SELECT * FROM B_BLOG A WHERE A.CREATE_TIME < (SELECT CREATE_TIME FROM B_BLOG WHERE ID=#{0}) AND DELETE_FLAG=0 ORDER BY A.CREATE_TIME DESC LIMIT 1 ")
    public Blog getPreOne(String id);

    @Select("SELECT A.*, B.ID AS ELDERID FROM B_BLOG A LEFT OUTER JOIN (SELECT T.*,#{0} AS TEMPID  FROM B_BLOG T WHERE T.CREATE_TIME < (SELECT CREATE_TIME FROM B_BLOG WHERE ID=#{0}) ORDER BY T.CREATE_TIME DESC LIMIT 1) B ON B.TEMPID=A.ID WHERE A.ID=#{0} AND A.DELETE_FLAG=0 ")
    public BlogVO getAttachElderId(String id);

    @Update("UPDATE B_BLOG SET DELETE_FLAG=1 WHERE ID=#{0}")
    void deleteById(String id);
}
