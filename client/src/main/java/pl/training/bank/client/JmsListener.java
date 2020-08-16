package pl.training.bank.client;

import javax.jms.*;
import javax.naming.NamingException;
import java.util.Scanner;

public class JmsListener {

    private static final String BANK_TOPIC_JNDI_NAME = "jms/topic/Bank";
    private static final String TOPIC_CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";

    private static MessageListener onMessage = message -> {
        try {
            System.out.println(message.getBody(String.class));
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    };

    public static void main(String[] args) throws NamingException {
        ProxyFactory proxyFactory = new ProxyFactory();
        ConnectionFactory connectionFactory = proxyFactory.createProxy(TOPIC_CONNECTION_FACTORY_JNDI_NAME);
        Topic topic = proxyFactory.createProxy(BANK_TOPIC_JNDI_NAME);
        try (JMSContext jmsContext = connectionFactory.createContext()) {
            JMSConsumer consumer = jmsContext.createConsumer(topic);
            consumer.setMessageListener(onMessage);
            new Scanner(System.in).next();
        }
    }

}
