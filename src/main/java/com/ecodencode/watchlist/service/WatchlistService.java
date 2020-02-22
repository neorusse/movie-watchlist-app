package com.ecodencode.watchlist.service;

import com.ecodencode.watchlist.exception.DuplicateTitleException;
import com.ecodencode.watchlist.model.WatchlistItem;
import com.ecodencode.watchlist.repository.WatchlistRepository;

import java.util.List;

public class WatchlistService {

  WatchlistRepository watchlistRepository = new WatchlistRepository();

  public List<WatchlistItem> getWatchlistItems(){
    return watchlistRepository.getList();
  }

  public int getWatchlistItemsSize() {
    return watchlistRepository.getList().size();
  }

  // limits users movie watch list to 5
  public void movieListLimit() throws DuplicateTitleException {

    if (watchlistRepository.getList().size() == 5) {
      throw new DuplicateTitleException();
    }

  }

  // method to retrieve an id of a watch list
  public WatchlistItem findWatchlistItemById(Integer id) {
    return watchlistRepository.findById(id);
  }

  public void addOrUpdateWatchlistItem(WatchlistItem watchlistItem) throws DuplicateTitleException {

    WatchlistItem existingItem = findWatchlistItemById(watchlistItem.getId());

    if (existingItem == null) {

      if (watchlistRepository.findByTitle(watchlistItem.getTitle()) != null) {
        throw new DuplicateTitleException();
      }

      watchlistRepository.addItem(watchlistItem);

    } else {
      existingItem.setComment(watchlistItem.getComment());
      existingItem.setPriority(watchlistItem.getPriority());
      existingItem.setRating(watchlistItem.getRating());
      existingItem.setTitle(watchlistItem.getTitle());
    }
  }

}
