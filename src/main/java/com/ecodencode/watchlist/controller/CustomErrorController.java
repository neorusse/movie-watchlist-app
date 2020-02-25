package com.ecodencode.watchlist;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

  // adding logs
  Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

  @Override
  public String getErrorPath() {
    return "/error";
  }

  @GetMapping("/error")
  public ModelAndView handleError(HttpServletResponse response) {

    int statusCode = response.getStatus();
    logger.error("Error with status code " + statusCode + " happened. Support! Do something about it!");

    return new ModelAndView("error");
  }

}