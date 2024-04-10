/**
 * @author teaho2015@gmail.com
 * since 2017/1/10
 */
package net.teaho.blog.server.api.controller;


import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.teaho.blog.server.common.Constants;
import net.teaho.blog.server.api.dto.*;
import net.teaho.blog.server.blog.domain.Blog;
import net.teaho.blog.server.blog.service.BlogService;
import net.teaho.blog.server.blog.vo.BlogVO;
import net.teaho.blog.server.common.exception.NotFoundException;
import net.teaho.blog.server.util.jdbc.support.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogAPIController {

    private final static int DEFAULT_PAGE_NUM = 1;

    private final BlogService blogService;

    @RequestMapping(value = "/page/{id:^\\d+$}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object getPage(@PathVariable("id") int id) throws NotFoundException {
        Page<Blog> page = blogService.getPage(id, Constants.BLOG.DEFAULT_PAGE_SIZE, true);
        log.info("convert page to dto");
        return PageDTO.newBuilder()
                .currentPageNum(page.getCurrentPageNo())
                .pageSize(page.getPageSize())
                .totalPageCount(page.getTotalPageCount())
                .data(page.getResult())
                .build();
    }

    @RequestMapping(value = "/article/{id:^\\w+$}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object getArticle(HttpServletRequest request, @PathVariable("id") String id) throws NotFoundException {
        Blog blog = blogService.getArticle(id);
        return BlogDTO.newBuilder().parse(blog).build();
    }



/*    @RequestMapping(value = "/article/{id:^\\w+$}", method = RequestMethod.DELETE)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object deleteArticle(HttpServletRequest request, @PathVariable("id") String id) {
        blogService.deleteArticle(id);
        return "success";
    }*/

    @RequestMapping(value = "/article/{id:^\\w+$}/attachElderId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object getArticleAttachElderId(HttpServletRequest request,@PathVariable("id") String id) throws NotFoundException {
        BlogVO blogVO = blogService.getArticleAttachElderId(id);
        return BlogAttachIdDTO.newBuilder().parse(blogVO).build();
    }

    @RequestMapping(value = "/article/{id:^\\w+$}/elderOne", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object getElderArticle(HttpServletRequest request,@PathVariable("id") String id) throws NotFoundException {
        Blog blog = blogService.getElderArticle(id);
        return BlogDTO.newBuilder().parse(blog).build();
    }


/*    @RequestMapping(value = "/article", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object postBlog(Blog blog, HttpServletRequest request) {
        Assert.hasText(blog.getImageUrl(), "image url null");
        Assert.hasText(blog.getTitle(), "title is null");
        Assert.hasText(blog.getTitleSecondary(), "title_secondary is null");
        Assert.hasText(blog.getContent(), "content is null");
        blogService.createBlog(blog);
        SimpleDataDTO<String> simpleDataDTO = new SimpleDataDTO<>();
        simpleDataDTO.setMessage("success");
        return simpleDataDTO;
    }

    @RequestMapping(value = "/article/{id:^\\w+$}", method = RequestMethod.PUT)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object putBlog(@RequestBody Blog blog, HttpServletRequest request, @PathVariable("id") String id) {
        Assert.hasText(blog.getImageUrl(), "image url null");
        Assert.hasText(blog.getTitle(), "title is null");
        Assert.hasText(blog.getTitleSecondary(), "title_secondary is null");
        Assert.hasText(blog.getContent(), "content is null");
        blog.setId(id);
        blogService.updateBlog(blog);
        SimpleDataDTO<String> simpleDataDTO = new SimpleDataDTO<>();
        simpleDataDTO.setMessage("success");
        return simpleDataDTO;
    }


    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object uploadImage(HttpServletRequest request, @RequestParam("image") MultipartFile image) throws IOException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("link", blogService.addImage(request, image));
        SimpleDataDTO<JSONObject> dataDTO = new SimpleDataDTO<>();
        dataDTO.setData(jsonObject);
        return dataDTO;
    }*/



    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleNotFoundException(HttpServletRequest request, NotFoundException e) {
//        request.removeAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE);
        return NotFoundDTO.newBuilder().defualtDocumentation_url().message("NotFoundException, "+ e.getMessage()).build();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.TEXT_PLAIN);
//        return new ResponseEntity<String>(ex.getMessage(), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleNullPointException(HttpServletRequest request, NullPointerException e) {
        log.error("error while request: " + request.getRequestURI(), e);
        return InternalErrorDTO.newBuilder().defualtDocumentation_url().message("NullPointerException, "+ e.getMessage()).build();
    }

    @ExceptionHandler({IOException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleIOException(HttpServletRequest request, IOException e) {
        List<String> list = Arrays.asList(e.getStackTrace()).stream()
                .map(StackTraceElement::toString)
                .collect(Collectors.toList());
        return InternalErrorDTO.newBuilder().defualtDocumentation_url().message("IOException, " + e.getMessage()).addErrors(list).build();
    }

}

