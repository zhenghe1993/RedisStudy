package com.jmper.mvc;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-15 14:40:41)
 */
@ControllerAdvice
public class AdviceController {

    @ModelAttribute("userName")
    public String getUserName(){
        return "jmper";
    }
    @ModelAttribute("password")
    public String getPassword(){
        return "123456";
    }




}
