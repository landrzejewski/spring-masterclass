package pl.training.cloud.payments.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient("payments-service")
public interface Payments {

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("payments")
    ResponseEntity<PaymentResponseDto> addPaymentRequest(@RequestBody PaymentsRequestDto paymentsRequestDto);

}
