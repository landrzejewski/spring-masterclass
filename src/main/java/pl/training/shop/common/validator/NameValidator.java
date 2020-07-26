package pl.training.shop.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

   private int minLength;

   public void initialize(Name constraint) {
      minLength = constraint.minLength();
   }

   public boolean isValid(String value, ConstraintValidatorContext context) {
      return value.length() >= minLength;
   }

}
