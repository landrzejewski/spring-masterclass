package pl.training.cloud.trips.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.training.cloud.payments.api.PaymentResponseDto;
import pl.training.cloud.payments.api.PaymentsRequestDto;

import java.util.Optional;

@Log
@Service
@RequiredArgsConstructor
public class BalancedRestTemplatePaymentsService implements PaymentsService {

    private static final String PAYMENTS_URI = "http://payments-service/payments";

    private final RestTemplate restTemplate;
    private final PaymentMapper paymentMapper;

    @Override
    public Optional<Long> pay(Long amount, Card card) {
        PaymentsRequestDto paymentsRequestDto = paymentMapper.toPaymentRequestDto(card);
        paymentsRequestDto.setAmount(amount);
        try {
            PaymentResponseDto paymentResponseDto = restTemplate.postForObject(PAYMENTS_URI, paymentsRequestDto, PaymentResponseDto.class);
            if (paymentResponseDto != null) {
                return Optional.ofNullable(paymentResponseDto.getId());
            }
        } catch (HttpClientErrorException exception) {
            log.warning("Payment failed");
        }
        return Optional.empty();
    }

}
