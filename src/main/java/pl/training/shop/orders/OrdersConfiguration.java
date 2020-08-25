package pl.training.shop.orders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.shop.mails.MailService;

@Configuration
public class OrdersConfiguration {

    @Bean
    public OrderService orderService(OrderRepository orderRepository, MailService mailService) {
        return new OrderService(orderRepository, mailService);
    }

}
