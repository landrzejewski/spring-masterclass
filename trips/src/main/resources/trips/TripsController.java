package pl.training.cloud.trips;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("trips/{tripId}")
    public ResponseEntity<TripDto> endTrip(@PathVariable Long tripId) {
        Trip trip = tripsService.endTrip(tripId);
        TripDto tripDto = tripMapper.toTripDto(trip);
        return ResponseEntity.ok(tripDto);
    }

}
