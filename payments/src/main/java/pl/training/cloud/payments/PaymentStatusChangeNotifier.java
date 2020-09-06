package pl.training.cloud.payments;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import pl.training.cloud.payments.api.PaymentResponseDto;

@Aspect
@Service
@RequiredArgsConstructor
public class PaymentStatusChangeNotifier {

    private final Source source;
    private final PaymentMapper paymentMapper;

    @AfterReturning(value = "@annotation(NotifyPaymentStatusChange)", returning = "payment")
    private void notifyChange(Payment payment) {
        PaymentResponseDto paymentResponseDto = paymentMapper.toPaymentResponseDto(payment);
        Message<PaymentResponseDto> message = MessageBuilder.withPayload(paymentResponseDto).build();
        source.output().send(message);
    }

}
