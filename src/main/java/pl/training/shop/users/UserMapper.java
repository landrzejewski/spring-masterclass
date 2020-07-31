package pl.training.shop.users;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.web.PagedResultTransferObject;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "emailAddress", target = "email")
    User toUser(UserTransferObject userTransferObject);

    @InheritInverseConfiguration
    UserTransferObject toUserTransferObject(User user);

    /*@IterableMapping(elementTargetType = UserTransferObject.class)
    List<UserTransferObject> toUserTransferObjects(List<User> users);*/

    PagedResultTransferObject<UserTransferObject> toUserTransferObjectsPage(PagedResult<User> usersPage);

}
