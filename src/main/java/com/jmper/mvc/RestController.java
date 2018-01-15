package com.jmper.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-14 19:00:37)
 */
@Controller
@RequestMapping(value = "/rest")
public class RestController {

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") int id) {
        return "get" + id;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Object post(@PathVariable("id") int id) {
        return "post" + id;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object put(@PathVariable("id") int id) {
        return "put" + id;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") int id) {
        return "delete" + id;
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView test(@ModelAttribute("userName") String userName,
                             @ModelAttribute("password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", userName);
        modelAndView.addObject("password", password);
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
