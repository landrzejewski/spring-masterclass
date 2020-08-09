package pl.training.payments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.ls.LSOutput;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

class PaymentsGeneratorTest {

    private final PaymentsGenerator paymentsGenerator = new PaymentsGenerator();

    @Test
    void shouldGeneratePayments() throws InterruptedException {
        /*CountDownLatch countDownLatch = new CountDownLatch(1);
        paymentsGenerator.paymentsStream(Duration.ofSeconds(1))
                //.filter(payment -> payment.getAmount() > 10)
                //.map(Payment::getTransactionId)
                .subscribe(this::onPayment, this::onError, countDownLatch::countDown);
        countDownLatch.await();*/

        StepVerifier.create(paymentsGenerator.paymentsStream(Duration.ofSeconds(1)).take(2))
                .recordWith(ArrayList::new)
                .consumeNextWith(Payment::hasTransactionId)
                .expectNextCount(1)
                .consumeRecordedWith(payments -> assertThat(payments).allMatch(Payment::hasTransactionId))
                .verifyComplete();
    }

    private void onPayment(Payment payment) {
        System.out.println(payment);
    }

    private void onError(Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
    }

}
