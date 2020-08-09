package pl.training.paymentsclient;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@ConfigurationProperties("payments")
@Setter
@Component
@RequiredArgsConstructor
public class PaymentsClientRunner implements CommandLineRunner {

    private String url;
    private String path;
    private final PaymentsRepository paymentsRepository;
    private final PaymentsR2DBCRepository paymentsR2DBCRepository;

    private Flux<Payment> getPayments() {
        return WebClient.builder()
                .baseUrl(url)
                .build()
                .get()
                .uri(path)
                .retrieve()
                .bodyToFlux(Payment.class);
    }

    @Override
    public void run(String... args) throws Exception {
        Flux<Payment> savedPayments = getPayments()
                .flatMap(paymentsRepository::save)
                .flatMap(paymentsR2DBCRepository::save)
                .share();

        savedPayments
                .map(Payment::getAmount)
                .subscribe(System.out::println);

        savedPayments
                .subscribe(System.out::println);
    }

}
