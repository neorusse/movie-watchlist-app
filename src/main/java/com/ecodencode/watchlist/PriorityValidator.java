package com.ecodencode.watchlist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// validator class for @Priority custom validator annotation
public class PriorityValidator implements ConstraintValidator<Priority, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    // remove whitespace from the value the user entered, & check if the value contains any of these letters: LHM
    return value.trim().length() == 1 && "LHM".contains(value.toUpperCase());
  }
}
