package com.ecodencode.watchlist.repository;

import com.ecodencode.watchlist.model.WatchlistItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WatchlistRepository {

  private List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();
  private static int index = 1;

  // get all movies
  public List<WatchlistItem> getList(){
    return watchlistItems;
  }

  // add new movie
  public void addItem(WatchlistItem watchlistItem) {
    watchlistItem.setId(index++);
    watchlistItems.add(watchlistItem);
  }

  // get a specific movie by Id
  public WatchlistItem findById(Integer id) {
    for (WatchlistItem watchlistItem : watchlistItems) {
      if (watchlistItem.getId().equals(id)) {
        return watchlistItem;
      }
    }
    return null;
  }

  // get a specific movie by title
  public WatchlistItem findByTitle(String title) {
    for (WatchlistItem watchlistItem : watchlistItems) {
      if (watchlistItem.getTitle().equals(title)) {
        return watchlistItem;
      }
    }
    return null;
  }

}

