package com.ecodencode.watchlist.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class MovieRatingService {

  String apiUrl = "http://www.omdbapi.com/?i=tt3896198&apikey=8abdb146&t=";

  public String getMovieRating(String title) {

    try {
      // RestTemplate is as a HTTP client, just like a browser but inside our code.
      RestTemplate template = new RestTemplate();

      // using Spring’s RestTemplate (like a browser)  helper class to hit the API URL
      ResponseEntity<ObjectNode> response = template.getForEntity(apiUrl + title, ObjectNode.class);

      // get the body of the response
      ObjectNode jsonObject = response.getBody();

      // extract the movie rating as text and return it.
      return jsonObject.path("imdbRating").asText();

    } catch (Exception e) {
      System.out.println("Something went wrong while calling OMDb API" + e.getMessage());
      return null;
    }
  }

}

