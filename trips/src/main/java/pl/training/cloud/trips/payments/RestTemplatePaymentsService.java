package pl.training.cloud.trips.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.training.cloud.payments.api.PaymentResponseDto;
import pl.training.cloud.payments.api.PaymentsRequestDto;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Log
@Service
@RequiredArgsConstructor
public class RestTemplatePaymentsService implements PaymentsService {

    private static final String PAYMENTS_SERVICE = "payments-service";
    private static final String PAYMENTS_RESOURCE = "/payments";
    private final int PREFERRED_INSTANCE = 0;

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    private final PaymentMapper paymentMapper;


    @Override
    public Optional<Long> pay(Long amount, Card card) {
        Optional<URI> requestUri = getRequestUri();
        if (requestUri.isEmpty()) {
            return Optional.empty();
        }
        return pay(requestUri.get(), amount, card);
    }

    private Optional<URI> getRequestUri() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(PAYMENTS_SERVICE);
        if (serviceInstances.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(serviceInstances.get(PREFERRED_INSTANCE).getUri().resolve(PAYMENTS_RESOURCE));
    }

    private Optional<Long> pay(URI requestUri, Long amount, Card card) {
        PaymentsRequestDto paymentsRequestDto = paymentMapper.toPaymentRequestDto(card);
        paymentsRequestDto.setAmount(amount);
        try {
            PaymentResponseDto paymentResponseDto = restTemplate.postForObject(requestUri, paymentsRequestDto, PaymentResponseDto.class);
            if (paymentResponseDto != null) {
                return Optional.ofNullable(paymentResponseDto.getId());
            }
        } catch (HttpClientErrorException exception) {
            log.warning("Payment failed");
        }
        return Optional.empty();
    }

}
