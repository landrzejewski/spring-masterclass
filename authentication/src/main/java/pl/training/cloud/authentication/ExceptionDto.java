package pl.training.cloud.authentication;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ExceptionDto {

    @NonNull
    private String description;

}
