package pl.training.shop.users;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import pl.training.shop.common.validator.OnCreate;
import pl.training.shop.common.validator.OnUpdate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserTransferObject extends RepresentationModel<UserTransferObject> {

    @NotEmpty(groups = { OnCreate.class, OnUpdate.class })
    private String firstName;
    @NotEmpty(groups = { OnCreate.class, OnUpdate.class })
    private String lastName;
    @NotEmpty(groups = OnCreate.class)
    @Email(groups = OnCreate.class)
    private String emailAddress;

}
