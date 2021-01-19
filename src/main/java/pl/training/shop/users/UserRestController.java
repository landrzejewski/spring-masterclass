package pl.training.shop.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.training.shop.common.validator.OnCreate;
import pl.training.shop.common.validator.OnUpdate;
import pl.training.shop.common.web.PagedResultTransferObject;
import pl.training.shop.common.web.UriBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Validated
@RequestMapping("${apiPrefix}/users")
@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UriBuilder uriBuilder = new UriBuilder();

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<UserTransferObject> addUser(@Valid @RequestBody UserTransferObject userTransferObject) {
        var user = userMapper.toUser(userTransferObject);
        var userId = userService.add(user).getId();
        var locationUri = uriBuilder.requestUriWithId(userId);
        return ResponseEntity.created(locationUri).build();
    }

    @Validated(OnUpdate.class)
    @PutMapping("{id}")
    public ResponseEntity<UserTransferObject> updateUser(@PathVariable @Min(1) Long id, @Valid @RequestBody UserTransferObject userTransferObject) {
        var user = userMapper.toUser(userTransferObject);
        user.setId(id);
        userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserTransferObject> getUser(@PathVariable Long id) {
        var user = userService.getById(id);
        var userTransferObject = userMapper.toUserTransferObject(user);
        userTransferObject.add(linkTo(methodOn(UserRestController.class).getUser(id)).withSelfRel());
        return ResponseEntity.ok(userTransferObject);
    }

    @GetMapping
    public PagedResultTransferObject<UserTransferObject> getUsersByLastName(
            @RequestParam String lastNameFragment,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize) {
        var users = userService.getByLastName(lastNameFragment, pageNumber, pageSize);
        return userMapper.toUserTransferObjectsPage(users);
    }

    /*@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionTransferObject> onUserNotFound(UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionTransferObject("User not found"));
    }*/

}
