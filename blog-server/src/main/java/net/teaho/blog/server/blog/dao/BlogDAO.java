/**
 * Created with IntelliJ IDEA.
 * User: tea
 * Date: 16-11-02
 * Time: 下午8:57
 */
package net.teaho.blog.server.blog.dao;

import net.teaho.blog.server.blog.domain.Blog;
import net.teaho.blog.server.blog.vo.BlogVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("blogDao")
public interface BlogDAO {
    @Insert("INSERT INTO B_BLOG (ID, IMAGE_URL, DATE, TITLE, TITLE_SECONDARY, CONTENT, CREATOR_ID, CREATOR_NAME, CREATE_TIME, DELETE_FLAG) VALUES (#{id}, #{image_url}, #{date}, #{title}, #{title_secondary}, #{content}, #{creator_id}, #{creator_name}, #{create_time}, #{delete_flag})")
    @Options(useGeneratedKeys = true, keyProperty = "ID")
    public void add(Blog blog);

    @Select("SELECT COUNT(*) FROM B_BLOG WHERE DELETE_FLAG = '0' ")
    public int getTotalNum();


    @Select("SELECT * FROM B_BLOG WHERE DELETE_FLAG=0 ORDER BY CREATE_TIME DESC LIMIT #{arg0},#{arg1}")
    public List<Blog> getBlogList(long preIndex, int range);



    @Select("SELECT * FROM B_BLOG A WHERE ID=#{arg0} AND DELETE_FLAG=0 ")
    public Blog get(String id);
    
/**
 *
 * below sql which gen rownum first is a hard way to get pre blog
 *    SELECT @rownum:=@rownum+1 rownum, id, CREATE_TIME From
 *    (SELECT @rownum:=0,b_blog.* FROM b_blog WHERE id!=''  ORDER BY CREATE_TIME limit 10) t
 */
    @Select("SELECT * FROM B_BLOG A WHERE A.CREATE_TIME < (SELECT CREATE_TIME FROM B_BLOG WHERE ID=#{arg0}) AND DELETE_FLAG=0 ORDER BY A.CREATE_TIME DESC LIMIT 1 ")
    public Blog getPreOne(String id);

    @Select("SELECT A.*, B.ID AS ELDERID FROM B_BLOG A LEFT OUTER JOIN (SELECT T.*,#{arg0} AS TEMPID  FROM B_BLOG T WHERE T.CREATE_TIME < (SELECT CREATE_TIME FROM B_BLOG WHERE ID=#{arg0}) AND TYPE=1 ORDER BY T.CREATE_TIME DESC LIMIT 1) B ON B.TEMPID=A.ID WHERE A.ID=#{arg0} AND A.DELETE_FLAG=0 ")
    public BlogVO getAttachElderId(String id);

    @Update("UPDATE B_BLOG SET DELETE_FLAG=1 WHERE ID=#{arg0}")
    void deleteById(String id);

    @Update("UPDATE B_BLOG SET TITLE=#{title}, TITLE_SECONDARY=#{title_secondary}, IMAGE_URL=#{image_url}, CONTENT=#{content} WHERE ID=#{id}")
    void updateById(Blog blog);
}
