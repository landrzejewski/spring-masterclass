package pl.training.cloud.trips;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TripDto {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
