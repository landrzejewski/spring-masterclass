package pl.training.shop.legacy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jndi.JndiTemplate;

import javax.jms.Queue;
import javax.naming.NamingException;

@EnableJms
@Configuration
public class LegacyConfiguration {

    @Bean
    public Queue messagesQueue() throws NamingException {
        return new JndiTemplate().lookup("jboss/exported/jms/queue/Shop", Queue.class);
    }

    @Bean
    public MessageService messageService() {
        return new MessageService();
    }

}
