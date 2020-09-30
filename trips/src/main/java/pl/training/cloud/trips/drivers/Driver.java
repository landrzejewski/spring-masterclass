package pl.training.cloud.trips.drivers;

import lombok.*;
import pl.training.cloud.trips.BaseEntity;
import pl.training.cloud.trips.payments.Card;

import javax.persistence.*;

@Table(name = "drivers")
@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Driver extends BaseEntity {

    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    private Card card;

}
