package pl.training.cloud.authentication;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@Log
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> onException(Exception exception, Locale locale) {
        log.finest("Exception:" + exception.getMessage());
        return createResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR, locale);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> onUserNotFound(UserNotFoundException exception, Locale locale) {
        return createResponse(exception, HttpStatus.NOT_FOUND, locale);
    }

    private ResponseEntity<ExceptionDto> createResponse(Exception exception, HttpStatus httpStatus, Locale locale) {
        String messageKey = exception.getClass().getSimpleName();
        String description;
        try {
            description = messageSource.getMessage(messageKey, null, locale);
        } catch (NoSuchMessageException ex) {
            description = messageKey;
        }
        ExceptionDto exceptionDto = new ExceptionDto(description);
        return ResponseEntity.status(httpStatus).body(exceptionDto);
    }

}
