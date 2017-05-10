/**
 * @author teaho2015@gmail.com
 * since 2017/2/16
 */
package com.tea.blog.controller;


import com.tea.Constants;
import com.tea.api.controller.BlogAPIController;
import com.tea.util.TestUtil;
import com.tea.blog.domain.Blog;
import com.tea.blog.domain.builder.BlogBuilder;
import com.tea.blog.exception.NotFoundException;
import com.tea.blog.service.BlogService;
import com.tea.blog.vo.BlogVO;
import com.tea.util.jdbc.support.Page;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-springMVC-restful.xml"
//        "classpath:spring/applicationContext-datasource.xml",
//        "classpath:spring/applicationContext-myBatis.xml",
//        "classpath:spring/blog/applicationContext-bean-blog.xml"
}
        /*, classes = {TestContext.class}*/)
@WebAppConfiguration
public class BlogAPIControllerTester {

    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    private BlogAPIController blogAPIController;

    @Mock
    private BlogService blogServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private BlogAPIController blogAPIControllerMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
//all switch to Mock, init below obj if needed in each method rather than here
//        this.blogAPIController = new BlogAPIController();
//        this.blogService = new BlogServiceImpl();
//        this.blogAPIController.setBlogService(blogService);

        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
//        reset(blogServiceMock);

//        this.mockMvc = MockMvcBuilders.standaloneSetup(blogAPIController).build();

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getPage_pageNotFound() throws Exception {
        when(blogServiceMock.getPage(1, Constants.BLOG.DEFAULT_PAGE_SIZE, true)).thenThrow(new NotFoundException("page is empty!"));

        mockMvc.perform(get("/api/v1/blog/page/{id}", 1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("NotFoundException, page is empty!")));

        verify(blogServiceMock, times(1)).getPage(1, Constants.BLOG.DEFAULT_PAGE_SIZE, true);
        verifyNoMoreInteractions(blogServiceMock);
    }

    @Test
    public void getPage_foundAndReturnBlogList() throws Exception {
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
                .date((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01"))
                .title("test2")
                .title_secondary("test2")
                .content("<p>这里写你的初始化内容</p>")
                .creator_id(Constants.USER.ID)
                .creator_name(Constants.USER.NAME)
                .build();
        Page page = new Page(1, 5, 5, Arrays.asList(blog, blog2));
        when(blogServiceMock.getPage(1, Constants.BLOG.DEFAULT_PAGE_SIZE, true)).thenReturn(page);

        mockMvc.perform(get("/api/v1/blog/page/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.pageSize", is(Constants.BLOG.DEFAULT_PAGE_SIZE)))
                .andExpect(jsonPath("$.currentPageNum", is(1)))
                .andExpect(jsonPath("$.totalPageCount", is(1)))

                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id", is("1")))
                .andExpect(jsonPath("$.data[0].image_url", is("imageurl")))
                .andExpect(jsonPath("$.data[0].date", is((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01").getTime())))
                .andExpect(jsonPath("$.data[0].title", is("test1")))
                .andExpect(jsonPath("$.data[0].title_secondary", is("test1")))
                .andExpect(jsonPath("$.data[0].content", is("<p>这里写你的初始化内容</p>")))
                .andExpect(jsonPath("$.data[0].creator_id", is(Constants.USER.ID)))
                .andExpect(jsonPath("$.data[0].creator_name", is(Constants.USER.NAME)))
                .andExpect(jsonPath("$.data[1].id", is("2")))
                .andExpect(jsonPath("$.data[1].image_url", is("imageurl2")))
                .andExpect(jsonPath("$.data[1].date", is((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01").getTime())))
                .andExpect(jsonPath("$.data[1].title", is("test2")))
                .andExpect(jsonPath("$.data[1].title_secondary", is("test2")))
                .andExpect(jsonPath("$.data[1].content", is("<p>这里写你的初始化内容</p>")))
                .andExpect(jsonPath("$.data[1].creator_id", is(Constants.USER.ID)))
                .andExpect(jsonPath("$.data[1].creator_name", is(Constants.USER.NAME)));

        verify(blogServiceMock, times(1)).getPage(1, Constants.BLOG.DEFAULT_PAGE_SIZE, true);
        verifyNoMoreInteractions(blogServiceMock);

    }

    @Test
    public void getArticleAttachElderId_notFound() throws Exception {
        when(blogServiceMock.getArticleAttachElderId("abc")).thenThrow(new NotFoundException("article not found!"));

        mockMvc.perform(get("/api/v1/blog/article/{id}/attachElderId", "abc"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message", is("NotFoundException, article not found!")));
//                .andExpect(redirectedUrl("f"));

        verify(blogServiceMock, times(1)).getArticleAttachElderId("abc");
        verifyNoMoreInteractions(blogServiceMock);
    }


    @Test
    public void getArticleAttachElderId_foundAndReturnArticleAttachElderId() throws Exception {
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
        when(blogServiceMock.getArticleAttachElderId("abc")).thenReturn(blogVO);

        mockMvc.perform(get("/api/v1/blog/article/{id}/attachElderId", "abc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is("abc")))
                .andExpect(jsonPath("$.elderid", is("2")))
                .andExpect(jsonPath("$.image_url", is("image_url")))
                .andExpect(jsonPath("$.date", is((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01").getTime())))
                .andExpect(jsonPath("$.title", is("title")))
                .andExpect(jsonPath("$.title_secondary", is("title_secondary")))
                .andExpect(jsonPath("$.content", is("<p>这里写你的初始化内容</p>")))
                .andExpect(jsonPath("$.creator_id", is(Constants.USER.ID)))
                .andExpect(jsonPath("$.creator_name", is(Constants.USER.NAME)));

        verify(blogServiceMock, times(1)).getArticleAttachElderId("abc");
        verifyNoMoreInteractions(blogServiceMock);
    }


    public WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }
}
