package pl.training.shop.legacy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.naming.NamingException;

@EnableJms
@Configuration
public class LegacyConfiguration {

    @Bean
    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        var defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        defaultJmsListenerContainerFactory.setConcurrency("5-10");
        return defaultJmsListenerContainerFactory;
    }

    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {
        return new JndiTemplate().lookup("java:/ConnectionFactory", ConnectionFactory.class);
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        var cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
        return new JmsTemplate(cachingConnectionFactory);
    }

    @Bean
    public JmsSender messageSender(JmsTemplate jmsTemplate, Queue messagesQueue) {
        return new JmsSender(jmsTemplate, messagesQueue);
    }

    @Bean
    public Queue messagesQueue() throws NamingException {
        return new JndiTemplate().lookup("jboss/exported/jms/queue/Shop", Queue.class);
    }

    @Bean
    public MessageService messageService() {
        return new MessageService();
    }

}
