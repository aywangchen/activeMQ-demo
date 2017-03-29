package com.demo;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * Created by wangc on 2017/3/29.
 */
public class SendMsg {

    private  String brokerURL = "";

    public SendMsg(String brokerURL) {
        this.brokerURL = brokerURL;
    }

    public void sendMsg(String msg) {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, brokerURL);
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("MesageQueue");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            ObjectMessage messages = session.createObjectMessage(msg);
            producer.send(messages);

            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
