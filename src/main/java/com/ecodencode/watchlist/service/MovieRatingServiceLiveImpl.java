package com.ecodencode.watchlist.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@ConditionalOnProperty(name = "app.environment", havingValue = "prod")
@Service
public class MovieRatingServiceLiveImpl implements MovieRatingService {

  String apiUrl = "http://www.omdbapi.com/?i=tt3896198&apikey=8abdb146&t=";

  private final Logger logger = LoggerFactory.getLogger(MovieRatingServiceLiveImpl.class);

  @Override
  public String getMovieRating(String title) {

    try {
      // RestTemplate is as a HTTP client, just like a browser but inside our code.
      RestTemplate template = new RestTemplate();

      logger.info("Calling OMDb API with url: {}", apiUrl + title);

      // using Spring’s RestTemplate (like a browser)  helper class to hit the API URL
      ResponseEntity<ObjectNode> response = template.getForEntity(apiUrl + title, ObjectNode.class);

      // get the body of the response
      ObjectNode jsonObject = response.getBody();

      logger.debug("OMDb API response: {}", jsonObject);

      // extract the movie rating as text and return it.
      return jsonObject.path("imdbRating").asText();

    } catch (Exception e) {
      logger.warn("Something went wrong while calling OMDb API" + e.getMessage());
      return null;
    }
  }

}

