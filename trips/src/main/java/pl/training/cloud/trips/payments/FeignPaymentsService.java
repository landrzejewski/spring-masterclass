package pl.training.cloud.trips.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.training.cloud.payments.api.PaymentResponseDto;
import pl.training.cloud.payments.api.Payments;
import pl.training.cloud.payments.api.PaymentsRequestDto;

import java.util.Optional;

@Primary
@Log
@Service
@RequiredArgsConstructor
public class FeignPaymentsService implements PaymentsService {

    private final Payments payments;
    private final PaymentsRepository paymentsRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Optional<Long> pay(Long amount, Card card) {
        PaymentsRequestDto paymentsRequestDto = paymentMapper.toPaymentRequestDto(card);
        paymentsRequestDto.setAmount(amount);
        try {
            PaymentResponseDto paymentResponseDto = payments.addPaymentRequest(paymentsRequestDto).getBody();
            if (paymentResponseDto != null) {
                return Optional.ofNullable(paymentResponseDto.getId());
            }
        } catch (HttpClientErrorException exception) {
            log.warning("Payment failed");
        }
        return Optional.empty();
    }

    @StreamListener(Sink.INPUT)
    public void updatePaymentStatus(PaymentResponseDto paymentResponseDto) {
        Payment payment = paymentMapper.toPayment(paymentResponseDto);
        log.info("Payment status changed: " + payment);
        paymentsRepository.findByTransactionId(payment.getTransactionId())
                .ifPresent(existingPayment -> {
                    existingPayment.setStatus(payment.getStatus());
                    paymentsRepository.saveAndFlush(existingPayment);
                });
    }

}
