package com.ecodencode.watchlist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RatingValidator implements ConstraintValidator<Rating, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    Double number;

    try {
      // convert value which is a String to Double (which is a number with decimal points)
      number = Double.parseDouble(value);
    } catch (NumberFormatException e) {
      return false;
    }

    if (number > 10 || number < 1) {
      return false;
    }

    return true;
  }
}
