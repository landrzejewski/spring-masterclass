package pl.training.shop.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.validator.Validate;
import pl.training.shop.payments.Payment;

import java.time.Instant;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order add(@Validate(exception = InvalidOrderException.class) Order order) {
        order.setTimestamp(Instant.now());
        order.setPayment(Payment.builder()
                .id(UUID.randomUUID().toString())
                .timestamp(Instant.now())
                .money(order.getTotalPrice())
                .build());
        return orderRepository.save(order);
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
