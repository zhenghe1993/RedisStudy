import com.jmper.mybatis.dao.UserMapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-03 17:29:28)
 */
public class ProxyTest {
    public static void main(String[] args) {

        ClassLoader classLoader = ProxyTest.class.getClassLoader();
        Class[] classes = new Class[]{UserMapper.class};
        InvocationHandler handler = new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (proxy instanceof UserMapper) {
                    System.out.println("userMapper");
                }
                System.out.println(Arrays.toString(args));
                System.out.println("hello ");
                
                return null;
            }
        };
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(classLoader, classes, handler);


        userMapper.selectOne(1);
    }
}
