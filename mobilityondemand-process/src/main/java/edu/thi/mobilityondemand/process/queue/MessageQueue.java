/**
 * @auther Sandro Käppner
 */

package edu.thi.mobilityondemand.process.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageQueue {

    private ActiveMQConnectionFactory connectionFactory = null;
    private Connection connection = null;
    private Session session = null;

    public Session createSession() throws JMSException {
        if (session == null) {
            connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD,
                    "tcp://activemq:61616");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        }
        return session;
    }

    public void sendMessageToQueue(String queueName, Message message) throws JMSException {
        MessageProducer producer = null;
        try {
            Destination destination = session.createQueue(queueName);
            producer = session.createProducer(destination);

            producer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        finally {
            if (producer != null) producer.close();
            if (session != null) session.close();
            if (connection != null) connection.close();
        }
    }
}
