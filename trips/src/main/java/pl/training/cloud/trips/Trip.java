package pl.training.cloud.trips;

import lombok.*;
import pl.training.cloud.trips.drivers.Driver;
import pl.training.cloud.trips.payments.Payment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "trips")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Trip extends BaseEntity {

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
