package com.example.springproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class StartController {
  @GetMapping("/start")
  public String start(Model model) {
    return "start";
  }

  @GetMapping("/select")
  public String select(Model model) {
    return "select";
  }


  @PostMapping("/start")
  public String startup(@ModelAttribute String repo, Model model) {
    return "select";
  }


}
