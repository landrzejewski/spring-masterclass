package pl.training.payments;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "10000")
public class PaymentsApplicationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnPayment() {
        webTestClient.get()
                .uri("/payment")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Payment.class)
                .consumeWith(result -> assertThat(result.getResponseBody()).hasSize(1));
    }

    @Test
    void shouldReturnPayments() throws InterruptedException {
        webTestClient.get()
                .uri("/payments")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_STREAM_JSON)
                .returnResult(Payment.class)
                .getResponseBody()
                .take(2)
                .subscribe(payment -> assertTrue(payment.hasTransactionId()));
    }

}
