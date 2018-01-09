import com.jmper.exception.LoginException;
import com.jmper.mybatis.utils.Root;
import com.jmper.shiro.ShiroConfig;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Before;
import org.junit.Test;

import java.security.Key;
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


        boolean flag = ShiroConfig.getSubject().hasRole("role3");

        if (flag) {
            logger.info("登陆成功！！！！");
        } else {
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
        boolean flag = ShiroConfig.getSubject().isPermitted("user:*");

        if (flag) {
            logger.info("登陆成功！！！！");
        } else {
            logger.info("此用户无权限登陆");
        }
        ShiroConfig.logout();

    }


    @Test
    public void encrypt() throws Exception {

        String name = "Jmper";
        String base64Encoded = Base64.encodeToString(name.getBytes());
        logger.info("base64Encoded={}", base64Encoded);
        String str2 = Base64.decodeToString(base64Encoded.getBytes());
        logger.info("str2={}", str2);

        String hexEncoded = Hex.encodeToString(name.getBytes());
        logger.info("hexEncoded={}", hexEncoded);
        str2 = new String(Hex.decode(hexEncoded));
        logger.info("str2={}", str2);
    }


    @Test
    public void sha() throws Exception {
        String userName = "Jmper";
        String password = "123456";
        String salt = userName + password;

        String md5 = new Md5Hash(password, salt).toString();

        logger.info("md5={}", md5);

        String sha256 = new Sha256Hash(password, salt).toString();

        logger.info("sha256={}", sha256);

        String simple = new SimpleHash("SHA-1", password, salt).toString();

        logger.info("simple={}", simple);

    }

    @Test
    public void defaultHash() throws Exception {

        DefaultHashService defaultHashService = new DefaultHashService();

        defaultHashService.setHashAlgorithmName("SHA-512");
        defaultHashService.setPrivateSalt(new SimpleByteSource("123"));
        defaultHashService.setGeneratePublicSalt(true);
        defaultHashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        defaultHashService.setHashIterations(1);

        HashRequest request = new HashRequest.Builder().
                setAlgorithmName("MD5").
                setSource(ByteSource.Util.bytes("hello")).
                setSalt(ByteSource.Util.bytes("123")).setIterations(2).
                build();
        String hex = defaultHashService.computeHash(request).toHex();
        System.out.println(hex);
    }

    @Test
    public void random() throws Exception {

        SecureRandomNumberGenerator randomNumberGenerator =
                new SecureRandomNumberGenerator();
        randomNumberGenerator.setSeed("123".getBytes());
        String hex = randomNumberGenerator.nextBytes().toHex();
        System.out.println(hex);
    }

    @Test
    public void aes() throws Exception {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        Key key = aesCipherService.generateNewKey();
        String text = "jmper";
        String encrpt = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        System.out.println(encrpt);
        encrpt = new String(aesCipherService.decrypt(Hex.decode(encrpt), key.getEncoded()).getBytes());
        System.out.println(encrpt);
    }


    @Test
    public void testEncrypt() throws Exception {

        ShiroConfig.init("shiro-role");

    }
}
