package com.ecodencode.watchlist;

import com.ecodencode.watchlist.model.WatchlistItem;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LessSixRatedMovieValidator implements ConstraintValidator<LessSixRatedMovie, WatchlistItem> {

  @Override
  public boolean isValid(WatchlistItem value, ConstraintValidatorContext context) {

    return !(Double.parseDouble(value.getRating()) < 6 &&  value.getComment().length() > 25 );
  }
}
