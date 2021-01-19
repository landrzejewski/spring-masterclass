package pl.training.shop.users;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersConfiguration {

    @Bean
    public UserService productService(UsersRepository usersRepository, UserMapper userMapper) {
        return new UserService(usersRepository, userMapper);
    }

}
