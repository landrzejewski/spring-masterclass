package pl.training.cloud.trips.payments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentsRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByTransactionId(Long transactionId);

}
