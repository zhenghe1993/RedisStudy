package com.jmper.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-07 21:41:43)
 */
public class MysqlRealm extends AuthenticatingRealm {


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        String userName = usernamePasswordToken.getUsername();

        System.out.println("用户名：" + userName);


        if ("unknow".equals(userName)) {
            throw new UnknownAccountException("用户不存在");
        }

        Object principle = userName;
        Object credentials = "123456";
        String realName = getName();
        return new SimpleAuthenticationInfo(principle, credentials, realName);
    }
}
