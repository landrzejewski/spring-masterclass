package pl.training.shop.orders;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.validator.Validate;
import pl.training.shop.payments.Payment;

import javax.mail.internet.MimeMessage;
import java.time.Instant;
import java.util.UUID;
import java.util.logging.Level;

@Transactional
@Log
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final JavaMailSender mailSender;

    public Order add(@Validate(exception = InvalidOrderException.class) Order order) {
        order.setTimestamp(Instant.now());
        order.setPayment(Payment.builder()
                .id(UUID.randomUUID().toString())
                .timestamp(Instant.now())
                .money(order.getTotalPrice())
                .build());
        sendEmail();
        return orderRepository.save(order);
    }

    //@Scheduled(cron = "*/10 * * * * *")
    public void printSummary() {
        log.log(Level.INFO, "Placed orders: " + orderRepository.count());
    }

    private void sendEmail() {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("shop@training.pl");
            mimeMessageHelper.setTo("xxx");
            mimeMessageHelper.setSubject("New order");
            mimeMessageHelper.setText("New order has been placed", true);
        };
        mailSender.send(messagePreparator);
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);
    }

    public void update(Order order) {
        orderRepository.save(order);
    }

    public PagedResult<Order> getAll(int pageNumber, int pageSize) {
        var orderPage = orderRepository.findAll(PageRequest.of(pageNumber,pageSize));
        return new PagedResult<>(orderPage.getContent(), pageNumber, orderPage.getTotalPages());
    }

}
