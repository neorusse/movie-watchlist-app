package com.ecodencode.watchlist;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.ecodencode.watchlist.model.WatchlistItem;
import com.ecodencode.watchlist.repository.WatchlistRepository;
import com.ecodencode.watchlist.service.MovieRatingService;
import com.ecodencode.watchlist.service.WatchlistService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistServiceTest {

  @Mock
  private WatchlistRepository watchlistRepositoryMock;

  @Mock
  private MovieRatingService movieRatingServiceMock;

  @InjectMocks
  private WatchlistService watchlistService;

  @Test
  public void testGetWatchlistItemsReturnsAllItemsFromRepository() {

    // Arrange
    WatchlistItem item1 = new WatchlistItem("Star Wars", "7.7", "M" , "" );
    WatchlistItem item2 = new WatchlistItem("Star Trek", "8.8", "M" , "");

    List<WatchlistItem> mockItems = Arrays.asList(item1, item2);

    when(watchlistRepositoryMock.getList()).thenReturn(mockItems);

    // Act
    List<WatchlistItem> result = watchlistService.getWatchlistItems();

    // Assert
    Assert.assertEquals(2, result.size());
    Assert.assertEquals("Star Wars", result.get(0).getTitle());
    Assert.assertEquals("Star Trek", result.get(1).getTitle());
  }

  @Test
  public void testGetWatchlistItemsRatingFormOMDbServiceOverrideTheValueInItems() {

    //Arrange
    WatchlistItem item1 = new WatchlistItem("Star Wars", "7.7", "M" , "" );
    List<WatchlistItem> mockItems = Collections.singletonList(item1);

    when(watchlistRepositoryMock.getList()).thenReturn(mockItems);
    when(movieRatingServiceMock.getMovieRating(any(String.class))).thenReturn("10");

    //Act
    List<WatchlistItem> result = watchlistService.getWatchlistItems();

    //Assert
    Assert.assertEquals("10", result.get(0).getRating());
  }
}
