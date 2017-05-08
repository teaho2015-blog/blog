/**
 * @author teaho2015@gmail.com
 * since 2017/2/17
 */
package com.tea.blog.controller;


import com.tea.Constants;
import com.tea.util.TestUtil;
import com.tea.blog.domain.Blog;
import com.tea.blog.domain.builder.BlogBuilder;
import com.tea.blog.exception.NotFoundException;
import com.tea.blog.service.BlogService;
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
import static org.hamcrest.Matchers.hasItem;
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
public class BlogControllerTester {

    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    private BlogController blogController;

    @Mock
    private BlogService blogServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private BlogController blogControllerMock;

//    @Mock
//    private BlogDAO blogDAOMock;

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
    public void viewBlog_foundAndRender() throws Exception {
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
        Page page = new Page(1, 2, 5, Arrays.asList(blog, blog2));
        when(blogServiceMock.getPage(BlogController.DEFAULT_PAGE_NUM)).thenReturn(page);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("forward:" + BlogController.BLOG_HOME_URL))
                .andExpect(forwardedUrl("/page/blog/blog_home.jsp"))
                .andExpect(model().attribute(BlogController.MODEL_NAME_BLOGLIST, hasSize(2)))
                .andExpect(model().attribute(BlogController.MODEL_NAME_BLOGLIST, hasItem(
                        allOf(
                                hasProperty("id", is("1")),
                                hasProperty("image_url", is("imageurl")),
                                hasProperty("date", is((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01"))),
                                hasProperty("title", is("test1")),
                                hasProperty("title_secondary", is("test1")),
                                hasProperty("content", is("<p>这里写你的初始化内容</p>")),
                                hasProperty("creator_id", is(Constants.USER.ID)),
                                hasProperty("creator_id", is(Constants.USER.NAME))
                        )
                )))
                .andExpect(model().attribute(BlogController.MODEL_NAME_BLOGLIST, hasItem(
                        allOf(
                                hasProperty("id", is("2")),
                                hasProperty("image_url", is("imageurl2")),
                                hasProperty("date", is((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-02"))),
                                hasProperty("title", is("test2")),
                                hasProperty("title_secondary", is("test2")),
                                hasProperty("content", is("<p>这里写你的初始化内容2</p>")),
                                hasProperty("creator_id", is(Constants.USER.ID)),
                                hasProperty("creator_id", is(Constants.USER.NAME))
                        )
                )));

        verify(blogServiceMock, times(1)).getPage(BlogController.DEFAULT_PAGE_NUM);
        verifyNoMoreInteractions(blogServiceMock);
    }


    @Test
    public void viewBlogPage_notFound() throws Exception {
        String exStr = "page is empty!";
        when(blogServiceMock.getPage(BlogController.DEFAULT_PAGE_NUM)).thenThrow(new NotFoundException(exStr));
        mockMvc.perform(get("/page/{pageNum}", "1"))
                .andExpect(status().isNotFound())
                .andExpect(view().name(TestUtil.generateForwardViewPath(Constants.URL.HTTPCODE_404)))
                .andExpect(forwardedUrl(Constants.URL.HTTPCODE_404));
        verify(blogServiceMock, times(1)).getPage(BlogController.DEFAULT_PAGE_NUM);
        verifyNoMoreInteractions(blogServiceMock);
    }

    @Test
    public void viewBlogPage_foundAndRender() throws Exception {
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
        Page page = new Page(1, 2, 5, Arrays.asList(blog, blog2));
        when(blogServiceMock.getPage(BlogController.DEFAULT_PAGE_NUM)).thenReturn(page);

        mockMvc.perform(get("/page/{pageNum}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("forward:" + BlogController.BLOG_HOME_URL))
                .andExpect(forwardedUrl("/page/blog/blog_home.jsp"))
                .andExpect(model().attribute(BlogController.MODEL_NAME_BLOGLIST, hasSize(2)))
                .andExpect(model().attribute(BlogController.MODEL_NAME_BLOGLIST, hasItem(
                        allOf(
                                hasProperty("id", is("1")),
                                hasProperty("image_url", is("imageurl")),
                                hasProperty("date", is((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-01"))),
                                hasProperty("title", is("test1")),
                                hasProperty("title_secondary", is("test1")),
                                hasProperty("content", is("<p>这里写你的初始化内容</p>")),
                                hasProperty("creator_id", is(Constants.USER.ID)),
                                hasProperty("creator_id", is(Constants.USER.NAME))
                        )
                )))
                .andExpect(model().attribute(BlogController.MODEL_NAME_BLOGLIST, hasItem(
                        allOf(
                                hasProperty("id", is("2")),
                                hasProperty("image_url", is("imageurl2")),
                                hasProperty("date", is((new SimpleDateFormat("YYYY-mm-dd")).parse("2017-01-02"))),
                                hasProperty("title", is("test2")),
                                hasProperty("title_secondary", is("test2")),
                                hasProperty("content", is("<p>这里写你的初始化内容2</p>")),
                                hasProperty("creator_id", is(Constants.USER.ID)),
                                hasProperty("creator_id", is(Constants.USER.NAME))
                        )
                )));

        verify(blogServiceMock, times(1)).getPage(BlogController.DEFAULT_PAGE_NUM);
        verifyNoMoreInteractions(blogServiceMock);
    }

    @Test
    public void viewAboutPage_render() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name(TestUtil.generateForwardViewPath(BlogController.ABOUT_URL)))
                .andExpect(forwardedUrl(BlogController.ABOUT_URL));
    }

    @Test
    public void viewArticlePage_render() throws Exception {
        mockMvc.perform(get("/article/{id}", "abc"))
                .andExpect(status().isOk())
                .andExpect(view().name(TestUtil.generateForwardViewPath(BlogController.ARTICLE_URL)+ "?id=" + "abc"))
                .andExpect(forwardedUrl(BlogController.ARTICLE_URL+ "?id=" + "abc"));
    }

    public WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }
}
