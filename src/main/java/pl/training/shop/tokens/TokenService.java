package pl.training.shop.tokens;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public Token createToken(Long ownerId) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token(tokenValue, ownerId);
        return tokenRepository.saveAndFlush(token);
    }

    public Token getToken(String tokenValue) {
        return tokenRepository.getByValue(tokenValue)
                .orElseThrow(TokenNotFoundException::new);
    }

    public void deleteToken(Token token) {
        tokenRepository.delete(token);
    }

}
