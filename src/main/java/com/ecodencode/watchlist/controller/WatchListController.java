package com.ecodencode.watchlist.controller;

import com.ecodencode.watchlist.model.WatchlistItem;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WatchListItemController {

  private WatchlistService watchlistService = new WatchlistService();

  // GET - Displays all Watch List
  @GetMapping("/watchlist")
  public ModelAndView getWatchlist() {

    String viewName = "watchlist";
    Map<String, Object> model = new HashMap<String, Object>();

    model.put("watchlistItems", watchlistItems);
    model.put("numberOfMovies", watchlistItems.size());

    return new ModelAndView(viewName, model);
  }

  // Get - Display watch list form
  @GetMapping("/watchlistItemForm")
  public ModelAndView showWatchlistItemForm(@RequestParam(required=false) Integer id) {

    String viewName = "watchlistItemForm";

    Map<String, Object> model = new HashMap<String, Object>();

    WatchlistItem watchlistItem = findWatchlistItemById(id);

    if (watchlistItem == null) {
      model.put("watchlistItem", new WatchlistItem());
    } else {
      model.put("watchlistItem", watchlistItem);
    }

    return new ModelAndView(viewName, model);
  }

  // method to retrieve an id of a watch list
  private WatchlistItem findWatchlistItemById(Integer id) {
    for (WatchlistItem watchlistItem : watchlistItems) {
      if (watchlistItem.getId().equals(id)) {
        return watchlistItem;
      }
    }

    return null;
  }

  // checks if the title of newly created movie already exist in the movie list
  private boolean itemAlreadyExists(String title) {

    for (WatchlistItem watchlistItem : watchlistItems) {
      if (watchlistItem.getTitle().equals(title)) {
        return true;
      }
    }

    return false;
  }

  // limits users movie watch list to 7
  private boolean movieListLimit() {

      if (watchlistItems.size() == 7) {
        return true;
      }

    return false;
  }

  // POST - Handles Watch List form submission for creating new item and updating an item
  @PostMapping("/watchlistItemForm")
  public ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return new ModelAndView("watchlistItemForm");
    }

    WatchlistItem existingItem = findWatchlistItemById(watchlistItem.getId());

    if (existingItem == null) {

      // calling itemAlreadyExists private method
      if (itemAlreadyExists(watchlistItem.getTitle())) {
        bindingResult.rejectValue("title", "", "This movie is already on your watchlist");

        return new ModelAndView("watchlistItemForm");
      }

      // calling movieListLimit private method
      if (movieListLimit()) {
        bindingResult.rejectValue(null, "", "You can only add 7 movies to your watchlist");

        return new ModelAndView("watchlistItemForm");
      }

      watchlistItem.setId(index++);
      watchlistItems.add(watchlistItem);
    } else {
      existingItem.setComment(watchlistItem.getComment());
      existingItem.setPriority(watchlistItem.getPriority());
      existingItem.setRating(watchlistItem.getRating());
      existingItem.setTitle(watchlistItem.getTitle());
    }

    RedirectView redirect = new RedirectView();
    redirect.setUrl("/watchlist");

    return new ModelAndView(redirect);
  }

}