package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.annotation.*;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.Locale;

//@Order(50)
@Aspect
@Log
@RequiredArgsConstructor
public class PaymentConsoleLogger implements Ordered {

    private static final String MESSAGE_KEY = "paymentInfo";

    private final MessageSource messageSource;

    @Pointcut("@annotation(LogPayments)")
    public void logPayments() {
    }

    @Before(value = "logPayments() && args(paymentRequest)")
    public void beforePayment(PaymentRequest paymentRequest) {
        log.info("New payment request: " + paymentRequest);
    }

    @After("logPayments()")
    public void afterPayment() {
        log.info("After payment");
    }

    @AfterThrowing(value = "logPayments()", throwing = "exception")
    public void onException(Exception exception) {
        log.info("Payment exception: " + exception.getClass().getSimpleName());
    }

    @AfterReturning(value = "logPayments()", returning = "payment")
    public void log(Payment payment) {
        log.info(createLogEntry(payment));
    }

    private String createLogEntry(Payment payment) {
        return messageSource.getMessage(MESSAGE_KEY, new String[] { payment.getMoney().toString() }, Locale.getDefault());
    }

    @Override
    public int getOrder() {
        return 50;
    }

}
