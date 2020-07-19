package pl.training.shop.orders;

import lombok.RequiredArgsConstructor;
import pl.training.shop.common.validator.Validate;

@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order add(@Validate(exception = InvalidOrderException.class) Order order) {
        return orderRepository.save(order);
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);
    }

    public void update(Order order) {
        orderRepository.save(order);
    }

}
