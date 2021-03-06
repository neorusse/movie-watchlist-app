package com.ecodencode.watchlist.controller;

import com.ecodencode.watchlist.exception.DuplicateTitleException;
import com.ecodencode.watchlist.model.WatchlistItem;
import com.ecodencode.watchlist.service.WatchlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WatchListController {

  private final Logger logger = LoggerFactory.getLogger(WatchListController.class);

  // field
  private WatchlistService watchlistService;

  // parameterized constructor
  @Autowired
  public WatchListController(WatchlistService watchlistService) {
    super();
    this.watchlistService = watchlistService;
  }

  // GET - Displays all Watch List
  @GetMapping("/watchlist")
  public ModelAndView getWatchlist() {

    logger.info("HTTP GET request received at /watchlist url");

    String viewName = "watchlist";

    Map<String, Object> model = new HashMap<String, Object>();

    model.put("watchlistItems", watchlistService.getWatchlistItems());
    model.put("numberOfMovies", watchlistService.getWatchlistItemsSize());

    return new ModelAndView(viewName, model);
  }

  // Get - Display watch list form
  @GetMapping("/watchlistItemForm")
  public ModelAndView showWatchlistItemForm(@RequestParam(required=false) Integer id) {

    logger.info("HTTP GET request received at /watchlistItemForm url");

    String viewName = "watchlistItemForm";

    Map<String, Object> model = new HashMap<String, Object>();

    WatchlistItem watchlistItem = watchlistService.findWatchlistItemById(id);

    if (watchlistItem == null) {
      model.put("watchlistItem", new WatchlistItem());
    } else {
      model.put("watchlistItem", watchlistItem);
    }

    return new ModelAndView(viewName, model);

  }

  // POST - Handles Watch List form submission for creating new item and updating an item
  @PostMapping("/watchlistItemForm")
  public ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {

    logger.info("HTTP POST request received at /watchlistItemForm url");

    if (bindingResult.hasErrors()) {
      return new ModelAndView("watchlistItemForm");
    }

    try {
      watchlistService.movieListLimit();

      try {
        watchlistService.addOrUpdateWatchlistItem(watchlistItem);
      } catch (DuplicateTitleException e) {
        bindingResult.rejectValue("title", "", "This title already exists on your watchlist");
        return new ModelAndView("watchlistItemForm");
      }

    } catch (DuplicateTitleException e) {
      bindingResult.rejectValue(null, "", "You can only add 5 movies to your watchlist");
      return new ModelAndView("watchlistItemForm");
    }

    RedirectView redirect = new RedirectView();
    redirect.setUrl("/watchlist");

    return new ModelAndView(redirect);
  }

}
