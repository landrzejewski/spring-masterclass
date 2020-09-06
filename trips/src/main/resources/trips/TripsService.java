package pl.training.cloud.trips;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.training.cloud.trips.drivers.Driver;
import pl.training.cloud.trips.drivers.DriversService;
import pl.training.cloud.trips.payments.Payment;
import pl.training.cloud.trips.payments.PaymentStatus;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Transactional
@Service
@RequiredArgsConstructor
public class TripsService {

    private final TripsRepository tripsRepository;
    private final DriversService driversService;

    public Trip startTrip(Long driverId) {
        Driver driver = driversService.getDriverById(driverId);
        Trip trip = new Trip(LocalDateTime.now(), driver);
        trip.setPayment(new Payment(PaymentStatus.NOT_STARTED));
        return tripsRepository.saveAndFlush(trip);
    }

    public Trip endTrip(Long tripId) {
        Trip trip = tripsRepository.findById(tripId)
                .orElseThrow(TripNotFoundException::new);
        if (trip.getPayment().getStatus() == PaymentStatus.NOT_STARTED) {
            trip.setEndTime(LocalDateTime.now());
            // payment
            tripsRepository.saveAndFlush(trip);
        }
        return trip;
    }


}
