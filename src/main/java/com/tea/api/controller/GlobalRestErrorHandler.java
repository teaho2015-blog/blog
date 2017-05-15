package com.tea.api.controller;

import com.tea.api.dto.MethodNotSupportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalRestErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalRestErrorHandler.class);

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @RequestMapping(value = "/api/v1/**")
    @ResponseBody
    public Object handleError405(HttpServletRequest request, Exception e)   {
//        ModelAndView mav = new ModelAndView("/405");
//        .addObject("exception", e);

        LOGGER.info("handleError405(HttpServletRequest request, Exception e)");
        //get stacktrace info
        List<String> list = Arrays.asList(e.getStackTrace()).stream()
                .map(StackTraceElement::toString)
                .collect(Collectors.toList());

        return MethodNotSupportDTO.newBuilder()
                .defualtDocumentation_url()
                .message(e.getMessage())
                .addErrrors(list)
                .build();
    }

}
