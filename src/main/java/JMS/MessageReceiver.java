package JMS;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageReceiver
{

    /*
     * URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is on localhost
     *
     * default broker URL is : tcp://localhost:61616"
     */
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;


    private static String queueName = "MESSAGE_QUEUE";

    public static void main(String[] args) throws JMSException
    {
        System.out.println("url = " + url);

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();


        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(queueName);


        MessageConsumer messageConsumer= session.createConsumer(destination);
        Message message=messageConsumer.receive();
        if(message instanceof TextMessage){
            TextMessage textMessage= (TextMessage) message;
            System.out.println("Receiver message " + textMessage.getText()+ " ");
        }
        connection.close();
    }
}
