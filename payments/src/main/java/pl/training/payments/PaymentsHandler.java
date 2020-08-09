package pl.training.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class PaymentsHandler {

    private static final Duration PAYMENTS_DURATION = Duration.ofSeconds(1);

    private final PaymentsGenerator paymentsGenerator;

    public Mono<ServerResponse> getPayment(ServerRequest request) {
        return createResponse(paymentsGenerator.paymentsStream(PAYMENTS_DURATION).take(1), MediaType.APPLICATION_JSON);
    }

    public Mono<ServerResponse> getPayments(ServerRequest request) {
        return createResponse(paymentsGenerator.paymentsStream(PAYMENTS_DURATION), MediaType.APPLICATION_STREAM_JSON);
    }

    private Mono<ServerResponse> createResponse(Flux<Payment> paymentsFlux, MediaType mediaType) {
        return ServerResponse.ok()
                .contentType(mediaType)
                .body(paymentsFlux, Payment.class);
    }

}
