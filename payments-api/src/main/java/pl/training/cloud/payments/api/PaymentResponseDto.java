package pl.training.cloud.payments.api;

import lombok.Data;

@Data
public class PaymentResponseDto {

    private Long id;
    private PaymentStatusDto status;

}
