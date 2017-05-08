/**
 * @author teaho2015@gmail.com
 * since 2017/2/17
 */
package com.tea.blog.service;


import com.tea.Constants;
import com.tea.blog.dao.BlogDAO;
import com.tea.blog.vo.BlogVO;
import com.tea.blog.domain.Blog;
import com.tea.blog.domain.builder.BlogBuilder;
import com.tea.blog.exception.NotFoundException;
import com.tea.util.jdbc.support.Page;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BlogServiceImplTester {

    @InjectMocks
    private BlogService blogService;

    @Mock
    private BlogDAO blogDAOMock;

    private static final int PAGE_NUM = 1;
    private static final int PAGE_RESULT_TOTAL_NUM = 2;
    private static final String ARTICLE_ID = "id";

    @Before
    public void setup() {
        blogService = new BlogServiceImpl();
//        ((BlogServiceImpl) blogService).setBlogDAO(blogDAOMock);
        MockitoAnnotations.initMocks(this);
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
//        reset(BlogDAOMock);

    }


    @Test
    public void getPage_executeDAOAndFound() throws Exception {


        Blog blog = BlogBuilder.newBlog()
                .id("1")
                .image_url("imageurl")
                .date((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01"))
                .title("test1")
                .title_secondary("test1")
                .content("<p>这里写你的初始化内容</p>")
                .creator_id(Constants.USER.ID)
                .creator_name(Constants.USER.NAME)
                .build();
        Blog blog2 = BlogBuilder.newBlog()
                .id("2")
                .image_url("imageurl2")
                .date((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-02"))
                .title("test2")
                .title_secondary("test2")
                .content("<p>这里写你的初始化内容2</p>")
                .creator_id(Constants.USER.ID)
                .creator_name(Constants.USER.NAME)
                .build();
        when(blogDAOMock.getTotalNum()).thenReturn(PAGE_RESULT_TOTAL_NUM);
        List blogList = Arrays.asList(blog, blog2);
        when(blogDAOMock.getBlogList(Page.getStartOfPage(PAGE_NUM, Constants.BLOG.DEFAULT_PAGE_SIZE), Constants.BLOG.DEFAULT_PAGE_SIZE)).thenReturn(blogList);

        Page page = blogService.getPage(PAGE_NUM, Constants.BLOG.DEFAULT_PAGE_SIZE, true);

        //验证调用 verify invoking
        verify(blogDAOMock, times(1)).getTotalNum();
        verify(blogDAOMock, times(1)).getBlogList(Page.getStartOfPage(PAGE_NUM, Constants.BLOG.DEFAULT_PAGE_SIZE), Constants.BLOG.DEFAULT_PAGE_SIZE);
        verifyNoMoreInteractions(blogDAOMock);

        assertThat(page.getResult(), is(blogList));
        assertThat("number not match", String.valueOf(page.getCurrentPageNo()), matchesPattern("^\\d+$"));
        assertEquals(page.getPageSize(), Constants.BLOG.DEFAULT_PAGE_SIZE);

    }


    @Test(expected = NotFoundException.class)
    public void getPage_notFound_shouldThrowNotFoundException() throws Exception {

        when(blogDAOMock.getTotalNum()).thenReturn(0);
        when(blogDAOMock.getBlogList(Page.getStartOfPage(PAGE_NUM, Constants.BLOG.DEFAULT_PAGE_SIZE), Constants.BLOG.DEFAULT_PAGE_SIZE)).thenReturn(null);

        Page page = blogService.getPage(PAGE_NUM, Constants.BLOG.DEFAULT_PAGE_SIZE, true);

        //验证调用 verify invoking
        verify(blogDAOMock, times(1)).getTotalNum();
        verify(blogDAOMock, times(1)).getBlogList(Page.getStartOfPage(PAGE_NUM, Constants.BLOG.DEFAULT_PAGE_SIZE), Constants.BLOG.DEFAULT_PAGE_SIZE);
        verifyNoMoreInteractions(blogDAOMock);
    }

    @Test
    public void getArticle_foundAndReturnBlog() throws Exception {

        Blog blog = BlogBuilder.newBlog()
                .id("1")
                .image_url("imageurl")
                .date((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01"))
                .title("test1")
                .title_secondary("test1")
                .content("<p>这里写你的初始化内容</p>")
                .creator_id(Constants.USER.ID)
                .creator_name(Constants.USER.NAME)
                .build();
        when(blogDAOMock.get(ARTICLE_ID)).thenReturn(blog);

        Blog actual = blogService.getArticle(ARTICLE_ID);

        //验证调用 verify invoking
        verify(blogDAOMock, times(1)).get(ARTICLE_ID);
        verifyNoMoreInteractions(blogDAOMock);

        assertThat(actual, is(blog));
    }

    @Test(expected = NotFoundException.class)
    public void getArticle_notFound_shouldThrowException() throws Exception {
        when(blogDAOMock.get(ARTICLE_ID)).thenReturn(null);

        Blog actual = blogService.getArticle(ARTICLE_ID);

        verify(blogDAOMock, times(1)).get(ARTICLE_ID);
        verifyNoMoreInteractions(blogDAOMock);
    }

    @Test(expected = NotFoundException.class)
    public void getArticle_foundButNotIncludeId_shouldThrowException() throws Exception {
        Blog blog = BlogBuilder.newBlog()
                .id("1")
                .image_url("imageurl")
                .date((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01"))
                .title("test1")
                .title_secondary("test1")
                .content("<p>这里写你的初始化内容</p>")
                .creator_id(Constants.USER.ID)
                .creator_name(Constants.USER.NAME)
                .build();
        blog.setId(null);
        when(blogDAOMock.get(ARTICLE_ID)).thenReturn(blog);

        Blog actual = blogService.getArticle(ARTICLE_ID);

        verify(blogDAOMock, times(1)).get(ARTICLE_ID);
        verifyNoMoreInteractions(blogDAOMock);
    }

    @Test(expected = NotFoundException.class)
    public void getArticle_foundButIDisEmpty_shouldThrowException() throws Exception {
        Blog blog = BlogBuilder.newBlog()
                .id("1")
                .image_url("imageurl")
                .date((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01"))
                .title("test1")
                .title_secondary("test1")
                .content("<p>这里写你的初始化内容</p>")
                .creator_id(Constants.USER.ID)
                .creator_name(Constants.USER.NAME)
                .build();
        blog.setId("");
        when(blogDAOMock.get(ARTICLE_ID)).thenReturn(blog);

        Blog actual = blogService.getArticle(ARTICLE_ID);

        verify(blogDAOMock, times(1)).get(ARTICLE_ID);
        verifyNoMoreInteractions(blogDAOMock);
    }

    @Test
    public void getArticleAttachElderId_foundAndReturnVO() throws Exception {
        BlogVO blogVO = new BlogVO();
        blogVO.setId("abc");
        blogVO.setElderid("2");
        blogVO.setImage_url("image_url");
        blogVO.setDate((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01"));
        blogVO.setTitle("title");
        blogVO.setTitle_secondary("title_secondary");
        blogVO.setContent("<p>这里写你的初始化内容</p>");
        blogVO.setCreator_id(Constants.USER.ID);
        blogVO.setCreator_name(Constants.USER.NAME);
        when(blogDAOMock.getAttachElderId(ARTICLE_ID)).thenReturn(blogVO);

        BlogVO actual = blogService.getArticleAttachElderId(ARTICLE_ID);

        verify(blogDAOMock, times(1)).getAttachElderId(ARTICLE_ID);
        verifyNoMoreInteractions(blogDAOMock);
        assertThat(actual, is(blogVO));
    }

    @Test(expected = NotFoundException.class)
    public void getArticleAttachElderId_foundButNotIncludeId_shouldThrowException() throws Exception {
        BlogVO blogVO = new BlogVO();
        blogVO.setElderid("2");
        blogVO.setImage_url("image_url");
        blogVO.setDate((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01"));
        blogVO.setTitle("title");
        blogVO.setTitle_secondary("title_secondary");
        blogVO.setContent("<p>这里写你的初始化内容</p>");
        blogVO.setCreator_id(Constants.USER.ID);
        blogVO.setCreator_name(Constants.USER.NAME);
        when(blogDAOMock.getAttachElderId(ARTICLE_ID)).thenReturn(blogVO);

        BlogVO actual = blogService.getArticleAttachElderId(ARTICLE_ID);

        verify(blogDAOMock, times(1)).get(ARTICLE_ID);
        verifyNoMoreInteractions(blogDAOMock);
    }

    @Test(expected = NotFoundException.class)
    public void getArticleAttachElderId_foundButIDisEmpty_shouldThrowException() throws Exception {
        BlogVO blogVO = new BlogVO();
        blogVO.setId("");
        blogVO.setElderid("2");
        blogVO.setImage_url("image_url");
        blogVO.setDate((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01"));
        blogVO.setTitle("title");
        blogVO.setTitle_secondary("title_secondary");
        blogVO.setContent("<p>这里写你的初始化内容</p>");
        blogVO.setCreator_id(Constants.USER.ID);
        blogVO.setCreator_name(Constants.USER.NAME);
        when(blogDAOMock.getAttachElderId(ARTICLE_ID)).thenReturn(blogVO);

        BlogVO actual = blogService.getArticleAttachElderId(ARTICLE_ID);

        verify(blogDAOMock, times(1)).get(ARTICLE_ID);
        verifyNoMoreInteractions(blogDAOMock);
    }


    @Test
    public void getElderArticle_foundAndReturnBlog() throws Exception {

        Blog blog = BlogBuilder.newBlog()
                .id("1")
                .image_url("imageurl")
                .date((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01"))
                .title("test1")
                .title_secondary("test1")
                .content("<p>这里写你的初始化内容</p>")
                .creator_id(Constants.USER.ID)
                .creator_name(Constants.USER.NAME)
                .build();
        when(blogDAOMock.getPreOne(ARTICLE_ID)).thenReturn(blog);

        Blog actual = blogService.getElderArticle(ARTICLE_ID);

        //验证调用 verify invoking
        verify(blogDAOMock, times(1)).getPreOne(ARTICLE_ID);
        verifyNoMoreInteractions(blogDAOMock);

        assertThat(actual, is(blog));
    }

    @Test(expected = NotFoundException.class)
    public void getElderArticle_notFound_shouldThrowException() throws Exception {
        when(blogDAOMock.getPreOne(ARTICLE_ID)).thenReturn(null);

        Blog actual = blogService.getElderArticle(ARTICLE_ID);

        verify(blogDAOMock, times(1)).getPreOne(ARTICLE_ID);
        verifyNoMoreInteractions(blogDAOMock);
    }

    @Test(expected = NotFoundException.class)
    public void getElderArticle_foundButNotIncludeId_shouldThrowException() throws Exception {
        Blog blog = BlogBuilder.newBlog()
                .id("1")
                .image_url("imageurl")
                .date((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01"))
                .title("test1")
                .title_secondary("test1")
                .content("<p>这里写你的初始化内容</p>")
                .creator_id(Constants.USER.ID)
                .creator_name(Constants.USER.NAME)
                .build();
        blog.setId(null);
        when(blogDAOMock.getPreOne(ARTICLE_ID)).thenReturn(blog);

        Blog actual = blogService.getElderArticle(ARTICLE_ID);

        verify(blogDAOMock, times(1)).getPreOne(ARTICLE_ID);
        verifyNoMoreInteractions(blogDAOMock);
    }

    @Test(expected = NotFoundException.class)
    public void getElderArticle_foundButIDisEmpty_shouldThrowException() throws Exception {
        Blog blog = BlogBuilder.newBlog()
                .id("1")
                .image_url("imageurl")
                .date((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01"))
                .title("test1")
                .title_secondary("test1")
                .content("<p>这里写你的初始化内容</p>")
                .creator_id(Constants.USER.ID)
                .creator_name(Constants.USER.NAME)
                .build();
        blog.setId("");
        when(blogDAOMock.getPreOne(ARTICLE_ID)).thenReturn(blog);

        Blog actual = blogService.getElderArticle(ARTICLE_ID);

        verify(blogDAOMock, times(1)).getPreOne(ARTICLE_ID);
        verifyNoMoreInteractions(blogDAOMock);
    }


}
