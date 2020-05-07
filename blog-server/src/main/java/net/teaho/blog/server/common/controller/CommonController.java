/**
 * @author teaho2015@gmail.com
 * since 2017/1/10
 */
package net.teaho.blog.server.common.controller;


import lombok.extern.slf4j.Slf4j;
import net.teaho.blog.server.common.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping("/development")
public class CommonController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    Object view_development_page() {
        log.info("{/development}, method = RequestMethod.GET");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:" + Constants.COMMON.DEVELOPMENT_PAGE_URL);
        return mv;
    }

}

