package pl.training.bank.api.disposition;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.training.bank.api.validator.Unsigned;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class DispositionDto implements Serializable {

    @Pattern(regexp = "\\d{26}")
    @NotNull
    @NonNull
    private String accountNumber;
    //@Min(1)
    @Unsigned
    @NonNull
    private long funds;
    @NotNull
    @NonNull
    private String operationName;

}
