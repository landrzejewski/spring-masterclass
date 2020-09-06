package pl.training.cloud.trips.drivers;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DriversRepository extends JpaRepository<Driver, Long> {
}
