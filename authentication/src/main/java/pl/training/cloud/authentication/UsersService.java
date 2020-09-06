package pl.training.cloud.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public User addUser(User user) {
        return  usersRepository.saveAndFlush(user);
    }

    public User getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

}
