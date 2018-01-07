import com.jmper.exception.LoginException;
import com.jmper.mybatis.utils.Root;
import com.jmper.shiro.ShiroConfig;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-07 21:32:09)
 */

public class ShiroTest implements Root {


    @Test
    public void name() throws Exception {

        ShiroConfig.init("shiro-jdbc");
        try {
            ShiroConfig.login("jmper", "123456");
        } catch (AuthenticationException | LoginException e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("登陆成功！！！！");
        ShiroConfig.logout();

    }

    @Test
    public void allSuccess() throws Exception {

        ShiroConfig.init("shiro-all-success");
        try {
            ShiroConfig.login("jmper", "123456");
        } catch (AuthenticationException | LoginException e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("登陆成功！！！！");

        PrincipalCollection principalCollection = ShiroConfig.getSubject().getPrincipals();

        principalCollection.asList().forEach(System.out::println);

        ShiroConfig.logout();

    }

    @Test
    public void role() throws Exception {

        ShiroConfig.init("shiro-role");
        try {
            ShiroConfig.login("jmper", "123456");
        } catch (AuthenticationException | LoginException e) {
            logger.error(e.getMessage(), e);
        }


        boolean flag=ShiroConfig.getSubject().hasRole("role3");

        if(flag){
            logger.info("登陆成功！！！！");
        }else{
            logger.info("此用户无权限登陆");
        }
        ShiroConfig.logout();

    }

    @Test
    public void permit() throws Exception {

        ShiroConfig.init("shiro-role");
        try {
            ShiroConfig.login("jmper", "123456");
        } catch (AuthenticationException | LoginException e) {
            logger.error(e.getMessage(), e);
        }
        boolean flag=ShiroConfig.getSubject().isPermitted("user:*");

        if(flag){
            logger.info("登陆成功！！！！");
        }else{
            logger.info("此用户无权限登陆");
        }
        ShiroConfig.logout();

    }
}
