package pl.training.shop.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.training.shop.common.web.UriBuilder;

import javax.validation.Valid;

@RequestMapping("api/orders")
@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final UriBuilder uriBuilder = new UriBuilder();

    @PostMapping
    public ResponseEntity<OrderTransferObject> addOrder(@Valid @RequestBody OrderTransferObject orderTransferObject, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        var order = orderMapper.toOrder(orderTransferObject);
        var orderId = orderService.add(order).getId();
        var locationUri = uriBuilder.requestUriWithId(orderId);
        return ResponseEntity.created(locationUri).build();
    }

}
