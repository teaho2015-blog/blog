/**
 * @author teaho2015@gmail.com
 * since 2017/2/16
 */
package com.tea.blog;


import com.tea.blog.controller.BlogAPIController;
import com.tea.blog.service.BlogService;
import com.tea.blog.service.BlogServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-springMVC-restful.xml",
        "classpath:spring/applicationContext-datasource.xml",
        "classpath:spring/applicationContext-myBatis.xml",
        "classpath:spring/blog/applicationContext-bean-blog.xml"})
@WebAppConfiguration
public class BlogControllerTester {

    private MockMvc mockMvc;

    private BlogAPIController blogAPIController;

    private BlogService blogService;

    @Autowired
    private WebApplicationContext webApplicationContext;

//    @Autowired
//    private TodoService todoServiceMock;

    @Before
    public void setup() {
//        MockitoAnnotations.initMocks(this);
        this.blogAPIController = new BlogAPIController();
        this.blogService = new BlogServiceImpl();
        this.blogAPIController.setBlogService(blogService);

//        this.mockMvc = MockMvcBuilders.standaloneSetup(blogAPIController).build();

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCont() {
    /*    Todo first = new TodoBuilder()
                .id(1L)
                .description("Lorem ipsum")
                .title("Foo")
                .build();
        Todo second = new TodoBuilder()
                .id(2L)
                .description("Lorem ipsum")
                .title("Bar")
                .build();

        when(todoServiceMock.findAll()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].description", is("Lorem ipsum")))
                .andExpect(jsonPath("$[0].title", is("Foo")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].description", is("Lorem ipsum")))
                .andExpect(jsonPath("$[1].title", is("Bar")));

        verify(todoServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(todoServiceMock);*/
    }



    @Test
    public void getPage_ControllerNotfound() throws Exception {
        when(blogService.getPage(1))/*.thenThrow(new TodoNotFoundException(""))*/;

        mockMvc.perform(get("/api/todo/{id}", 1))
                .andExpect(status().isNotFound());

        verify(blogService, times(1)).getPage(1);
        verifyNoMoreInteractions(blogService);
    }

    @Test
    public void getPage_foundAndReturnBlogList() throws Exception {
/*        Blog blog = BlogBuilder.newBlog()
                .id("8dd31d2ef80d42998f4dbce834628003")
                .image_url("/static/images/blog/8dd31d2ef80d42998f4dbce834628003.jpg")
                .date((new SimpleDateFormat()).parse("2016-11-24"))
                .title("test1")
                .title_secondary("test2")
                .content("<p>这里写你的初始化内容</p>")
                .creator_id("SYSTEM_GENERATE")
                .creator_name("SYSTEM_GENERATE")
//                .create_time(new TimeStamp())
                .build();

        when(todoServiceMock.getPage(1)).thenReturn(found);*/

        mockMvc.perform(get("/api/v1/blog/page/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                /*.andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.description", is("Lorem ipsum")))
                .andExpect(jsonPath("$.title", is("Foo")))*/;

//        verify(todoServiceMock, times(1)).findById(1L);
//        verifyNoMoreInteractions(todoServiceMock);
    }


    public BlogService getBlogService() {
        return blogService;
    }

    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }


    public WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }
}
