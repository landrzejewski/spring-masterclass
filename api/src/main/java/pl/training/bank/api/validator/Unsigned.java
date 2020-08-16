package pl.training.bank.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UnsignedValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Unsigned {

    String message() default "Value must be greater then zero";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean enabled() default true;

}
