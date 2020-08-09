package pl.training.paymentsclient;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PaymentsRepository extends ReactiveMongoRepository<Payment, String> {
}
