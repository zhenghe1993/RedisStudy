package com.jmper.shiro;

import com.jmper.exception.LoginException;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;
import org.omg.CORBA.UnknownUserException;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-07 21:41:43)
 */
public class MysqlRealm implements Realm {


    @Override
    public String getName() {
        return MysqlRealm.class.getSimpleName();
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String userName = (String) authenticationToken.getPrincipal();
        String password = (String) authenticationToken.getCredentials();

        if ("jmper".equals(userName)) {
            throw new UnknownAccountException("此用户不存在");
        }

        if ("123456".equals(password)){
            throw new IncorrectCredentialsException("密码错误");
        }

        return new SimpleAuthenticationInfo(userName,password,getName());
    }
}
