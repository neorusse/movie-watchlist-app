package com.ecodencode.watchlist;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

  @Override
  public String getErrorPath() {
    return "/error";
  }

  @GetMapping("/error")
  public ModelAndView handleError(HttpServletResponse response) {

    int statusCode = response.getStatus();
    System.out.println("Error with status code " + statusCode + " happened. Support! Do something about it!");

    return new ModelAndView("error");
  }

}