package pl.training.cloud.trips.payments;

import java.util.Optional;

public interface PaymentsService  {

    Optional<Long> pay(Long amount, Card card);

}
