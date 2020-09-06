package pl.training.cloud.trips;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TripsRepository extends JpaRepository<Trip, Long> {
}
