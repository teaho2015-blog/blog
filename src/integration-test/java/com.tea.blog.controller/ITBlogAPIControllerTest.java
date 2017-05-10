/**
 * @author teaho2015@gmail.com
 * since 2017/4/20
 */
package com.tea.blog.controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.tea.Constants;
import com.tea.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-springMVC-restful.xml",
        "classpath:spring/applicationContext-datasource.xml",
        "classpath:spring/applicationContext-myBatis.xml",
        "classpath:spring/blog/applicationContext-bean-blog.xml"})
@WebAppConfiguration
//@DbUnitConfiguration
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("blogAPIData.xml")
public class ITBlogAPIControllerTest {
        @Autowired
        @Resource
        private WebApplicationContext webApplicationContext;

        private MockMvc mockMvc;

        @Before
        public void setUp() {
                mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        }


        @Test
        @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "blogAPIData.xml")
//        @DatabaseSetup("blogAPITest-datasource.xml")
        public void getPageById_FoundAndReturn() throws Exception {
//                PageDTO pageDTO = new PageDTO();
                mockMvc.perform(get("/api/v1/blog/page/{id}", 1))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("$.pageSize", is(Constants.BLOG.DEFAULT_PAGE_SIZE)))
                        .andExpect(jsonPath("$.currentPageNum", is(1)))
                        .andExpect(jsonPath("$.totalPageCount", is(1)))

                        .andExpect(jsonPath("$.data", hasSize(2)))
                        .andExpect(jsonPath("$.data[1].id", is("id1")))
                        .andExpect(jsonPath("$.data[1].image_url", is("/image/url.jpg")))
                        .andExpect(jsonPath("$.data[1].date", is((new SimpleDateFormat("YYYY-mm-dd HH:mm:ss")).parse("2017-01-01 00:00:00").getTime())))
                        .andExpect(jsonPath("$.data[1].title", is("fooTitle1")))
                        .andExpect(jsonPath("$.data[1].title_secondary", is("title_secondary1")))
                        .andExpect(jsonPath("$.data[1].content", is("content1")))
                        .andExpect(jsonPath("$.data[1].creator_id", is(Constants.USER.ID)))
                        .andExpect(jsonPath("$.data[1].creator_name", is(Constants.USER.NAME)))
                        .andExpect(jsonPath("$.data[1].create_time", is((new SimpleDateFormat("YYYY-mm-dd HH:mm:ss")).parse("2017-01-01 11:13:28").getTime())))
                        .andExpect(jsonPath("$.data[1].delete_flag", is(0)))
                        .andExpect(jsonPath("$.data[0].id", is("id2")))
                        .andExpect(jsonPath("$.data[0].image_url", is("/image/url2.jpg")))
                        .andExpect(jsonPath("$.data[0].date", is((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2017-01-02 00:00:00").getTime())))
                        .andExpect(jsonPath("$.data[0].title", is("fooTitle2")))
                        .andExpect(jsonPath("$.data[0].title_secondary", is("title_secondary2")))
                        .andExpect(jsonPath("$.data[0].content", is("content2")))
                        .andExpect(jsonPath("$.data[0].creator_id", is(Constants.USER.ID)))
                        .andExpect(jsonPath("$.data[0].creator_name", is(Constants.USER.NAME)))
                        .andExpect(jsonPath("$.data[0].create_time", is((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2017-01-02 11:13:28").getTime())))
                        .andExpect(jsonPath("$.data[0].delete_flag", is(0)))
//                        .andExpect(jsonPath("$.message", is("NotFoundException, page is empty!")))
                ;
        }

        @Test
        @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "blogAPIData.xml")
        public void getArticleById_AttachElderId_FoundAndReturn() throws Exception {

                mockMvc.perform(get("/api/v1/blog/article/{id}/attachElderId", "id2"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("$.id", is("id2")))
                        .andExpect(jsonPath("$.elderid", is("id1")))
                        .andExpect(jsonPath("$.image_url", is("/image/url2.jpg")))
                        .andExpect(jsonPath("$.date", is((new SimpleDateFormat("yyyy-MM-dd")).parse("2017-01-02").getTime())))
                        .andExpect(jsonPath("$.title", is("fooTitle2")))
                        .andExpect(jsonPath("$.title_secondary", is("title_secondary2")))
                        .andExpect(jsonPath("$.content", is("content2")))
                        .andExpect(jsonPath("$.creator_id", is(Constants.USER.ID)))
                        .andExpect(jsonPath("$.creator_name", is(Constants.USER.NAME)))
                        .andExpect(jsonPath("$.create_time", is((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2017-01-02 11:13:28").getTime())))
                        .andExpect(jsonPath("$.delete_flag", is(0)));

        }
}
