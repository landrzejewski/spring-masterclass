package pl.training.cloud.trips.payments;

import lombok.*;
import pl.training.cloud.trips.BaseEntity;

import javax.persistence.*;

@Table(name = "paymemts")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Payment extends BaseEntity {

    @NonNull
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private Long transactionId;

}
