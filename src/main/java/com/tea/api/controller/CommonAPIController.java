/**
 * @author teaho2015@gmail.com
 * since 2017/1/10
 */
package com.tea.api.controller;


import com.tea.api.dto.NotFoundDTO;
import com.tea.api.dto.RootDTO;
import com.tea.common.exception.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class CommonAPIController {

    /** 日志实例 */
    private  final Logger logger = Logger.getLogger(getClass());

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public @ResponseBody
    Object rootAPI() {
        return new RootDTO();
    }

    @RequestMapping(value = "/**", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public @ResponseBody
    Object defaultNotFound() {
        return NotFoundDTO.newBuilder().defualtDocumentation_url().message("Not Found!").build();
    }
}

