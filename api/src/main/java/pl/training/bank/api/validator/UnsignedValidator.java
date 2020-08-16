package pl.training.bank.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnsignedValidator implements ConstraintValidator<Unsigned, Long> {

    private boolean enabled;

    @Override
    public void initialize(Unsigned constraintAnnotation) {
        enabled = constraintAnnotation.enabled();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        return value > 0 || !enabled;
    }

}
