package com.ecodencode.watchlist;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.RetentionPolicy;

// @Priority custom validator annotation
@Target ({ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
@Constraint (validatedBy= PriorityValidator.class)
public @interface Priority {

  String message() default "Please enter L, M, or H for priority";

  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}