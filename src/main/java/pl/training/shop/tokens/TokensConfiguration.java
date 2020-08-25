package pl.training.shop.tokens;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokensConfiguration {

    @Bean
    public TokenService tokenService(TokenRepository tokenRepository) {
        return new TokenService(tokenRepository);
    }

}
