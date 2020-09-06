package pl.training.cloud.payments;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.training.cloud.payments.api.PaymentResponseDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

    PaymentResponseDto toPaymentResponseDto(Payment payment);

}
