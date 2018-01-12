import com.jmper.mybatis.dao.UserMapper;
import com.jmper.mybatis.entity.User;
import com.jmper.mybatis.utils.Root;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2017-12-27 10:12:12)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class SetTest implements Root {


    @Autowired
    private UserMapper userMapper;


    @Test
    public void name() throws Exception {

        User user = userMapper.selectOne(4);
        logger.info("----------user={}", user);
    }


}
