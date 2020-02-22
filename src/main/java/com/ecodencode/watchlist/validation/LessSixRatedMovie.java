package com.ecodencode.watchlist;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LessSixRatedMovieValidator.class)
public @interface LessSixRatedMovie {

  String message() default "Comment should be maximum 25 characters for movies with less than 6 rating";

  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
