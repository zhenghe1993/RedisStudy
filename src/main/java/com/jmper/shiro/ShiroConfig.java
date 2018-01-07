package com.jmper.shiro;

import com.jmper.exception.LoginException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-07 22:07:51)
 */
public class ShiroConfig {

    private static Subject subject = null;

    public static void init(String propertiesName) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:" + propertiesName + ".ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        subject = SecurityUtils.getSubject();
    }


    public static void login(String userName, String password) throws AuthenticationException,LoginException {
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        if (subject != null) {
            subject.login(token);
        } else {
            throw new LoginException("未初始化shiro");
        }
    }

    public static Subject getSubject() {
        return subject;
    }

    public static void logout() {
        if (subject != null) {
            subject.logout();
        }
    }

}
