package com.jmper.shiro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    public Object body(@RequestParam String param, @RequestHeader String head, @RequestBody String name) {

        return null;
    }

    public ModelAndView getView(){
        ModelAndView modelAndView=new ModelAndView();

        modelAndView.addObject("","");

        return modelAndView;
    }
}
