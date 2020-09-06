package pl.training.cloud.trips.drivers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriversService {

    private final DriversRepository driversRepository;

    public Driver getDriverById(Long id) {
        return driversRepository.findById(id)
                .orElseThrow(DriverNotFoundException::new);
    }

}
