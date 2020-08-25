package pl.training.shop.users;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.shop.common.TextSource;
import pl.training.shop.mails.MailService;
import pl.training.shop.tokens.TokenService;

@Configuration
public class UsersConfiguration {

    @Bean
    public UserService productService(UsersRepository usersRepository, TokenService tokenService, TextSource textSource, MailService mailService) {
        return new UserService(usersRepository, tokenService, textSource, mailService);
    }

}
