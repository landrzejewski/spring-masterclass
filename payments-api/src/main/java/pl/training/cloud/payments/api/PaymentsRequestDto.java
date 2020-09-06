package pl.training.cloud.payments.api;

import lombok.Data;

@Data
public class PaymentsRequestDto {

    private CardDto card;
    private Long amount;

}
