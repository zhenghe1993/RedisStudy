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
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );

        Connection connection = factory.createConnection();
        connection.start();

        //true/false 是否支持事务    签收模式
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue("first");
        MessageProducer producer = session.createProducer(null);


        for (int i = 0; i < 100; i++) {

            TextMessage message = session.createTextMessage("我是消息内容 - " + i);

            producer.send(destination, message);

            TimeUnit.SECONDS.sleep(1);
        }

        if (connection != null) {
            connection.close();
        }
    }
}
