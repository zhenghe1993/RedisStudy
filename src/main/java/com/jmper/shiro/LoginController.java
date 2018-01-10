package com.jmper.shiro;

import com.jmper.exception.LoginException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-10 20:27:59)
 */
@Controller
public class LoginController {

    @ResponseBody
    @RequestMapping(value = "/shiro/login.do", method = RequestMethod.POST)
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password) {

        System.out.println("userName = "+userName);
        System.out.println("password = "+password);
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            token.setRememberMe(true);
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("账户不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        } catch (LockedAccountException e) {
            System.out.println("账户锁定");
        }
        return "login";
    }

}
