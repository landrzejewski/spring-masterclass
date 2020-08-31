package pl.training.shop.mails;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiTemplate;
import org.springframework.mail.javamail.JavaMailSender;

import javax.jms.Queue;
import javax.naming.NamingException;

@EnableJms
@Configuration
public class MailsConfiguration {

    @Bean
    public Queue mailQueue() throws NamingException {
        return new JndiTemplate().lookup("jboss/exported/jms/queue/Mail", Queue.class);
    }

    @Bean
    public MailService mailService(JavaMailSender mailSender, JmsTemplate jmsTemplate, Queue mailQueue) {
        return new MailService(mailSender, jmsTemplate, mailQueue);
    }

}
