package pl.training.cloud.trips.drivers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.training.cloud.trips.payments.Card;

import javax.persistence.*;

@Table(name = "drivers")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @GeneratedValue
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Card card;

}
