package com.ecodencode.watchlist.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= RatingValidator.class)
public @interface Rating {

  String message() default "Rating should be a number between 1-10";

  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
