package pl.training.bank.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "exception")
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionDto {

    @NonNull
    private String description;

}
