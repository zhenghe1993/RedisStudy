import com.jmper.redis.RedisCacheUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2017-12-27 9:37:05)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class RedisTest {

    @Autowired
    private RedisCacheUtil<String, String, String> redisCacheUtil;


    @Test
    public void name() throws Exception {

//        ValueOperations<String, String> s = redisCacheUtil.setCacheObject("name", "hahaha");


       String res= redisCacheUtil.getCacheObject("name");
        System.out.println(res);
    }
}
