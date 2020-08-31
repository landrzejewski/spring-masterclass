package pl.training.shop.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiTemplate;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import pl.training.shop.common.profiler.Profiler;
import pl.training.shop.common.retry.MethodExecutor;
import pl.training.shop.common.validator.ModelValidator;
import pl.training.shop.common.validator.ValidatorService;
import pl.training.shop.legacy.JmsSender;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.naming.NamingException;
import javax.validation.Validator;

@Configuration
public class CommonConfiguration {

    @Bean
    public Profiler profiler() {
        return new Profiler();
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidatorService validatorService(Validator validator) {
        return new ValidatorService(validator);
    }

    @Bean
    public ModelValidator modelValidator(ValidatorService validatorService) {
        return new ModelValidator(validatorService);
    }

    @Bean
    public MethodExecutor methodExecutor() {
        return new MethodExecutor();
    }


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

}
