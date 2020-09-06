package pl.training.cloud.trips.payments;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.training.cloud.payments.api.PaymentResponseDto;
import pl.training.cloud.payments.api.PaymentsRequestDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

    PaymentsRequestDto toPaymentRequestDto(Card card);

    @Mapping(source = "id", target = "transactionId")
    Payment toPayment(PaymentResponseDto paymentResponseDto);

}
