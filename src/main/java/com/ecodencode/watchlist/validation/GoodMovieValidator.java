package com.ecodencode.watchlist.validation;

import com.ecodencode.watchlist.model.WatchlistItem;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchlistItem>{

  @Override
  public boolean isValid(WatchlistItem value, ConstraintValidatorContext context) {

    return !(Double.parseDouble(value.getRating()) >= 8 &&  "L".equals(value.getPriority().trim().toUpperCase()));
  }
}
