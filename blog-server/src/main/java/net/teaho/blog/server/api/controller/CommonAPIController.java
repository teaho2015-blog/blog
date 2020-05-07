/**
 * @author teaho2015@gmail.com
 * since 2017/1/10
 */
package net.teaho.blog.server.api.controller;


import lombok.extern.slf4j.Slf4j;
import net.teaho.blog.server.api.dto.NotFoundDTO;
import net.teaho.blog.server.api.dto.RootDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@Slf4j
public class CommonAPIController {

    /** 日志实例 */

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public @ResponseBody
    Object rootAPI() {
        return new RootDTO();
    }

    @RequestMapping(value = "/**", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public @ResponseBody
    Object defaultNotFound() {
        return NotFoundDTO.newBuilder().defualtDocumentation_url().message("Not Found!").build();
    }
}

