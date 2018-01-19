package com.jmper.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-18 23:25:17)
 */
public class Consumer {

    public static void main(String[] args) throws JMSException, InterruptedException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(
                "jmper",
                "123456",
                "tcp://localhost:61616"
        );

        Connection connection = factory.createConnection();
        connection.start();

        //true/false 是否支持事务    签收模式
        Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);

        Destination destination = session.createQueue("first");
        MessageConsumer consumer = session.createConsumer(destination);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {

            }
        });
        while (true) {
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println("接收到的数据  " + message.getText());
            TimeUnit.SECONDS.sleep(1);
            //手动签收 Session.CLIENT_ACKNOWLEDGE
            message.acknowledge();
        }

    }
}
