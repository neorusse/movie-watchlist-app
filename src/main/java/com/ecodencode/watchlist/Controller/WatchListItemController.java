package com.ecodencode.watchlist.Controller;

import com.ecodencode.watchlist.Model.WatchlistItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WatchListItemController {

  List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();

  // GET - Displays all Watch List
  @GetMapping("/watchlist")
  public ModelAndView getWatchlist() {

    String viewName = "watchlist";
    Map<String, Object> model = new HashMap<String, Object>();

    model.put("watchlistItems", watchlistItems);
    model.put("numberOfMovies", watchlistItems.size());

    return new ModelAndView(viewName, model);
  }
}
