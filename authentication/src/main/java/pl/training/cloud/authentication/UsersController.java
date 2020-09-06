package pl.training.cloud.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("users")
@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final UserMapper userMapper;
    private final UriBuilder uriBuilder = new UriBuilder();

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        User user = userMapper.toUser(userDto);
        Long userId = usersService.addUser(user).getId();
        URI locationUri = uriBuilder.requestUriWithAppendedId(userId);
        return ResponseEntity.created(locationUri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = usersService.getUserById(id);
        UserDto userDto = userMapper.toUserDto(user);
        return ResponseEntity.ok(userDto);
    }

}
