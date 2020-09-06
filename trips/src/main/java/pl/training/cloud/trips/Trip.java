package pl.training.cloud.trips;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.training.cloud.trips.drivers.Driver;
import pl.training.cloud.trips.payments.Payment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "trips")
@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Trip {

    @GeneratedValue
    @Id
    private Long id;
    @Column(name = "start_time")
    @NonNull
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @NonNull
    @OneToOne
    private Driver driver;
    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;

}
