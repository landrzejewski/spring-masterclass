package pl.training.cloud.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Random;

@Log
@Service
public class FakePaymentsService {

    private Random random = new Random();

    @NotifyPaymentStatusChange
    public Payment pay(Long amount, CreditCard creditCard) {
        Long paymentId = Math.abs(random.nextLong());
        log.info("Charging card: " + creditCard.getNumber() + " (amount: " + amount + ")");
        Payment payment = new Payment();
        payment.setId(paymentId);
        if (random.nextBoolean()) {
            payment.setStatus(PaymentStatus.CONFIRMED);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
        }
        return payment;
    }

}
