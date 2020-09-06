package pl.training.cloud.trips.payments;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Table(name = "paymemts")
@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Payment {

    @GeneratedValue
    @Id
    private Long id;
    @NonNull
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

}
