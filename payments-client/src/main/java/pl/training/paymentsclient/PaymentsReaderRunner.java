package pl.training.paymentsclient;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class PaymentsReaderRunner implements CommandLineRunner {

    private final PaymentsR2DBCRepository paymentsR2DBCRepository;

    @Override
    public void run(String... args) {
        Flux.interval(Duration.ofSeconds(1))
                .flatMap(tick -> paymentsR2DBCRepository.count())
                .subscribe(count -> System.out.println("Payment in h2 database: " + count));
    }

}
