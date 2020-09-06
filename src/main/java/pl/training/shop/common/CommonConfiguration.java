package pl.training.shop.common;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import pl.training.shop.common.profiler.Profiler;
import pl.training.shop.common.retry.MethodExecutor;
import pl.training.shop.common.validator.ModelValidator;
import pl.training.shop.common.validator.ValidatorService;

import javax.validation.Validator;

@EnableRabbit
@Configuration
public class CommonConfiguration implements RabbitListenerConfigurer {

    @Autowired
    private DefaultMessageHandlerMethodFactory messageHandlerMethodFactory;

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

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory(MappingJackson2MessageConverter consumerMessageConverter) {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerMessageConverter);
        return factory;
    }

    @Bean
    public MappingJackson2MessageConverter consumerMessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter producerMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
