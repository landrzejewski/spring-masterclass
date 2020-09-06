package pl.training.cloud.payments.api;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CardDto {

    private String number;
    private String cvv;
    private LocalDate expirationDate;

}
