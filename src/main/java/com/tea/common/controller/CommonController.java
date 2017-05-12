/**
 * @author teaho2015@gmail.com
 * since 2017/1/10
 */
package com.tea.common.controller;


import com.tea.Constants;
import com.tea.api.dto.NotFoundDTO;
import com.tea.api.dto.RootDTO;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/development")
public class CommonController {

    /** 日志实例 */
    private  final Logger logger = Logger.getLogger(getClass());


    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object view_development_page() {
        logger.info("{/development}, method = RequestMethod.GET");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:" + Constants.COMMON.DEVELOPMENT_PAGE_URL);
        return mv;
    }

}

