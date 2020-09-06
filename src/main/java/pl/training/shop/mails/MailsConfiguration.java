package pl.training.shop.mails;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailsConfiguration {

    @Bean
    public Queue sendEmailQueue(@Value("${send-email-queue}") String queueName) {
        return new Queue(queueName);
    }

    @Bean
    public MailService mailService(JavaMailSender mailSender, RabbitTemplate jmsTemplate, Queue mailQueue) {
        return new MailService(mailSender, jmsTemplate, mailQueue);
    }

}
