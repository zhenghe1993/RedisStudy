package com.jmper.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

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
        Object credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
        String realName = getName();
        return new SimpleAuthenticationInfo(principle, credentials, ByteSource.Util.bytes(userName), realName);
    }


    public static void main(String[] args) {
        Object res = new SimpleHash("MD5", "123456", ByteSource.Util.bytes("admin"), 1024);
        System.out.println(res);
    }
}
