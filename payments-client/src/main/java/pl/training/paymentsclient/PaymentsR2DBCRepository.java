package pl.training.paymentsclient;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PaymentsR2DBCRepository extends ReactiveCrudRepository<Payment, String> {
}

