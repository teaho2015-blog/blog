/**
 * @author teaho2015@gmail.com
 * since 2017/1/22
 */
package com.tea.blog.domain;

import com.tea.blog.domain.builder.BlogBuilder;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

public class BlogTest {

    private String ID = "id";
    private String IMAGE_URL = "image_url";
    private Date DATE = new Date();
    private String TITLE = "title";
    private String TITLE_SECONDARY = "title_secondary";
    private String CONTENT = "content";
    private String CREATOR_ID = "creator_id";
    private String CREATOR_NAME = "creator_name";
    private Timestamp CREATE_TIME = new Timestamp(System.currentTimeMillis());
    private String UPDATOR_ID = "updator_id";
    private String UPDATOR_NAME = "updator_name";
    private Timestamp UPDATE_TIME = new Timestamp(System.currentTimeMillis());
    private String DELETOR_ID = "deletor_id";
    private String DELETOR_NAME = "deletor_name";
    private Timestamp DELETE_TIME = new Timestamp(System.currentTimeMillis());
    private int DELETE_FLAG = 0;

    @Test
    public void build_shouldCreateNewObjectAndgenerateMandatoryInformation() throws IllegalAccessException {
        Blog blog = BlogBuilder.newBlog().build();

        assertNotNull(blog.getId());
        assertNull(blog.getContent());
        assertNull(blog.getImage_url());
        assertNull(blog.getTitle());
        assertNull(blog.getTitle_secondary());
        assertNull(blog.getDate());
        assertNull(blog.getCreator_id());
        assertNull(blog.getCreator_name());
        assertNull(blog.getCreate_time());
        assertEquals(DELETE_FLAG, blog.getDelete_flag());
    }

    @Test
    public void build_AllInformationGiven_ShouldCreateNewObjectAndSetAllInformation() {
        Blog blog = BlogBuilder.newBlog()
                .id(ID)
                .image_url(IMAGE_URL)
                .date(DATE)
                .title(TITLE)
                .title_secondary(TITLE_SECONDARY)
                .content(CONTENT)
                .creator_id(CREATOR_ID)
                .creator_name(CREATOR_NAME)
                .create_time(CREATE_TIME)
                .delete_flag(DELETE_FLAG)
                .build();
        assertEquals(ID, blog.getId());
        assertEquals(IMAGE_URL, blog.getImage_url());
        assertEquals(DATE, blog.getDate());
        assertEquals(TITLE, blog.getTitle());
        assertEquals(TITLE_SECONDARY, blog.getTitle_secondary());
        assertEquals(CONTENT, blog.getContent());
        assertEquals(CREATOR_NAME, blog.getCreator_name());
        assertEquals(CREATE_TIME, blog.getCreate_time());
        assertEquals(DELETE_FLAG, blog.getDelete_flag());
    }


   /* @Test
    public void preUpdate_ShouldUpdateOnlyModificationTime() {
        assertTrue(blog.getModificationTime().isAfter(todo.getCreationTime()));
    }*/

    private void pause(long timeInMillis) {
        try {
            Thread.currentThread().sleep(timeInMillis);
        }
        catch (InterruptedException e) {
            //Do Nothing
        }
    }
}
