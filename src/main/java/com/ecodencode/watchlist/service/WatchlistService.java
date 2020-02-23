package com.ecodencode.watchlist.service;

import com.ecodencode.watchlist.exception.DuplicateTitleException;
import com.ecodencode.watchlist.model.WatchlistItem;
import com.ecodencode.watchlist.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

  // fields
  private MovieRatingService movieRatingService;
  private WatchlistRepository watchlistRepository;

  // parameterized constructor
  @Autowired
  public WatchlistService(WatchlistRepository watchlistRepository, MovieRatingService movieRatingService) {
    super();
    this.watchlistRepository = watchlistRepository;
    this.movieRatingService = movieRatingService;
  }

  // Retrieve all movie watch list items
  public List<WatchlistItem> getWatchlistItems(){

    List<WatchlistItem> watchlistItems = watchlistRepository.getList();

    for (WatchlistItem watchlistItem : watchlistItems) {

      // get the IMDb rating of the movie
      String rating = movieRatingService.getMovieRating(watchlistItem.getTitle());

      if (rating != null) {
        watchlistItem.setRating(rating);
      }
    }

    return watchlistItems;
  }

  // get movie watch list size
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

  // adds new movie list or update existing movie list
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
