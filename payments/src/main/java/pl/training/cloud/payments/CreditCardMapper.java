package pl.training.cloud.payments;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.training.cloud.payments.api.CardDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditCardMapper {

    @Mapping(source = "expirationDate", target = "expiration")
    CreditCard toCreditCard(CardDto cardDto);

}
