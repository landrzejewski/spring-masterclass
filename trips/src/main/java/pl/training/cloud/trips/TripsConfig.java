package pl.training.cloud.trips;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.training.cloud.trips.drivers.Driver;
import pl.training.cloud.trips.drivers.DriversRepository;
import pl.training.cloud.trips.payments.Card;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@EnableBinding(Sink.class)
@EnableFeignClients(basePackages = "pl.training.cloud.payments.api")
@EnableSwagger2
@Configuration
@RequiredArgsConstructor
public class TripsConfig {

    private static final String BASE_PACKAGE = "pl.training.cloud.trips";

    private final DriversRepository driversRepository;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .build();
    }

    @PostConstruct
    public void init() {
        Card card = Card.builder()
                .id(1L)
                .number("123456789")
                .cvv("756")
                .expirationDate(LocalDate.now())
                .build();
        Driver driver = Driver.builder()
                .id(2L)
                .firstName("Jan")
                .lastName("Kowalski")
                .card(card)
                .build();
        driversRepository.saveAndFlush(driver);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
