package com.demo;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by wangc on 2017/3/29.
 */
public class RecevieMsg {
    private String brokerURL = "tcp://192.168.0.15:61616";

    public RecevieMsg(String brokerURL) {
        this.brokerURL = brokerURL;
    }

    public void recevieMsg() {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, brokerURL);
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("MesageQueue");
            MessageConsumer consumer = session.createConsumer(destination);

            while (true) {
                ObjectMessage message = (ObjectMessage) consumer.receive(10000);
                if (message != null) {
                    String messageContent = (String) message.getObject();
                    System.out.println("msg = [" + messageContent + "]");
                }else {
                    break;
                }
            }

            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
