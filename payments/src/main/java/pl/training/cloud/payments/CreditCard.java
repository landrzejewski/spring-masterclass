package pl.training.cloud.payments;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreditCard {

    private Long id;
    private String number;
    private String cvv;
    private LocalDate expiration;

}
