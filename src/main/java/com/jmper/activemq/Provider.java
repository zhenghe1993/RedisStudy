package com.jmper.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-18 23:25:17)
 */
public class Provider {

    public static void main(String[] args) throws JMSException, InterruptedException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(
                "jmper",
                "123456",
                "tcp://localhost:61616"
        );

        Connection connection = factory.createConnection();
        connection.start();

        //true/false 是否支持事务 支持事务需要 commit    签收模式
        Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
        Destination destination = session.createQueue("first");
        MessageProducer producer = session.createProducer(null);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        for (int i = 0; i < 10; i++) {

            TextMessage message = session.createTextMessage("我是消息内容 - " + i);

//             MapMessage message=session.createMapMessage();
//             message.setString("","");

            producer.send(destination, message);

            TimeUnit.SECONDS.sleep(1);
        }

        session.commit();

        if (connection != null) {
            connection.close();
        }
    }
}
