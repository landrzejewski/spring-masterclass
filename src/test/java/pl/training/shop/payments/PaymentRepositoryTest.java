package pl.training.shop.payments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.training.shop.payments.PaymentStatus.CANCELED;
import static pl.training.shop.payments.PaymentStatus.CONFIRMED;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    private final Payment firstPayment = Payment.builder()
            .id(UUID.randomUUID().toString())
            .money(LocalMoney.of(1_000))
            .status(CONFIRMED)
            .timestamp(Instant.now())
            .build();
    private final Payment secondPayment = Payment.builder()
            .id(UUID.randomUUID().toString())
            .money(LocalMoney.of(1_000))
            .status(CANCELED)
            .timestamp(Instant.now())
            .build();

    @BeforeEach
    void setUp() {
        testEntityManager.persist(firstPayment);
        testEntityManager.persist(secondPayment);
        testEntityManager.flush();
    }

    @Test
    void shouldReturnAllConfirmedPayments() {
        List<Payment> payments = paymentRepository.findByStatus(CONFIRMED);
        assertTrue(payments.contains(firstPayment));
        assertEquals(1, payments.size());
    }

}
