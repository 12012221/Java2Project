package com.example.springproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class commitController {
    @PostMapping(value = "/commit", params = "choose")
    public String showIssue(@ModelAttribute String repoName, Model model) {
        System.out.println("############################");
        System.out.println(repoName);
        return "commit";
    }

    @PostMapping("/commit")
    public String commBack() {
        return "redirect:/select";//issue跳转回select
    }
}
