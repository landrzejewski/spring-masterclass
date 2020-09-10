package pl.training.shop.users;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.TextSource;
import pl.training.shop.mails.MailMessage;
import pl.training.shop.mails.MailService;
import pl.training.shop.tokens.Token;
import pl.training.shop.tokens.TokenService;

import java.util.Map;
import java.util.function.Consumer;

@Transactional
@RequiredArgsConstructor
public class UserService {

    private static final String ACTIVATION_EMAIL_TEMPLATE = "activation-email";
    private static final String TOKEN_KEY = "token";
    private static final String USER_ID_KEY = "userId";
    private static final String SUBJECT_KEY = "email.subject";

    private final UsersRepository usersRepository;
    private final TokenService tokenService;
    private final TextSource textSource;
    private final MailService mailService;

    @Value("${defaultLanguage}")
    @Setter
    private String defaultLanguage;

    public User add(User user) {
        User savedUser = usersRepository.save(user);
        MailMessage mailMessage = prepareConfirmationEmail(savedUser, ACTIVATION_EMAIL_TEMPLATE);
        mailService.send(mailMessage);
        return savedUser;
    }

    public void activateUser(Long userId, String tokenValue) {
        updateAccount(user -> user.setEnabled(true), userId, tokenValue);
    }

    private void updateAccount(Consumer<User> updater, Long userId, String tokenValue) {
        Token token = tokenService.getToken(tokenValue);
        if (!userId.equals(token.getOwnerId())) {
            throw new IllegalStateException();
        }
        User user = getById(token.getOwnerId());
        updater.accept(user);
        usersRepository.saveAndFlush(user);
        tokenService.deleteToken(token);
    }

    public User getById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public PagedResult<User> getByLastName(String lastNameFragment, int pageNumber, int pageSize) {
        var userPage = usersRepository.findByLastNameContaining(lastNameFragment, PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(userPage.getContent(), pageNumber, userPage.getTotalPages());
    }

    public PagedResult<User> getAll(int pageNumber, int pageSize) {
        var userPage = usersRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(userPage.getContent(), pageNumber, userPage.getTotalPages());
    }

    private MailMessage prepareConfirmationEmail(User user, String templateName) {
        Token token = tokenService.createToken(user.getId());
        Map<String, Object> variables = Map.of(TOKEN_KEY, token.getValue(), USER_ID_KEY, user.getId());
        String subject = textSource.getText(SUBJECT_KEY, defaultLanguage);
        String text = textSource.getTextFromTemplate(templateName, variables, defaultLanguage);
        return new MailMessage(user.getEmail(), subject, text);
    }

}
