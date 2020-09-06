package pl.training.cloud.trips;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class TripsController {

    private final TripsService tripsService;
    private final TripMapper tripMapper;
    private final UriBuilder uriBuilder = new UriBuilder();

    @PostMapping("drivers/{driverId}/trips")
    public ResponseEntity<TripDto> startTrip(@PathVariable Long driverId) {
        Trip trip = tripsService.startTrip(driverId);
        URI locationUri = uriBuilder.requestUriWithAppendedId(trip.getId());
        TripDto tripDto = tripMapper.toTripDto(trip);
        return ResponseEntity.created(locationUri).body(tripDto);
    }

    @PutMapping("trips/{id}")
    public ResponseEntity<TripDto> endTrip(@PathVariable Long id) {
        Trip trip = tripsService.endTrip(id);
        TripDto tripDto = tripMapper.toTripDto(trip);
        return ResponseEntity.ok(tripDto);
    }

}
