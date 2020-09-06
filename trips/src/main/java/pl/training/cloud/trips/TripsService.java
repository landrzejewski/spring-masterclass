package pl.training.cloud.trips;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.training.cloud.trips.drivers.Driver;
import pl.training.cloud.trips.drivers.DriversService;
import pl.training.cloud.trips.payments.Card;
import pl.training.cloud.trips.payments.Payment;
import pl.training.cloud.trips.payments.PaymentStatus;
import pl.training.cloud.trips.payments.PaymentsService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class TripsService {

    private final TripsRepository tripsRepository;
    private final PaymentsService paymentsService;
    private final DriversService driversService;
    private final TripChargeConfig tripChargeConfig;

    public Trip startTrip(Long driverId) {
        Driver driver = driversService.getDriverById(driverId);
        Trip trip = new Trip(LocalDateTime.now(), driver);
        trip.setPayment(new Payment(PaymentStatus.NOT_STARTED));
        return tripsRepository.saveAndFlush(trip);
    }

    public Trip endTrip(Long tripId) {
        Trip trip = tripsRepository.findById(tripId)
                .orElseThrow(TripNotFoundException::new);
        Payment payment = trip.getPayment();
        if (payment.getStatus() == PaymentStatus.NOT_STARTED) {
            trip.setEndTime(LocalDateTime.now());
            Card card = trip.getDriver().getCard();
            paymentsService.pay(tripChargeConfig.getBaseCharge(), card).ifPresent(payment::setTransactionId);
            payment.setStatus(PaymentStatus.STARTED);
            tripsRepository.saveAndFlush(trip);
        }
        return trip;
    }

}
