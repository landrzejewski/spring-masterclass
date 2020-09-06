package pl.training.cloud.trips.payments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "cards")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @GeneratedValue
    @Id
    private Long id;
    @Column(name = "card_number")
    private String number;
    private String cvv;
    private LocalDate expirationDate;

}
