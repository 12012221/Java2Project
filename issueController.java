package com.example.springproject.web;

import com.example.springproject.json.JsonResult;
import com.example.springproject.service.issueService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class issueController {
    @Autowired
    private issueService issueService;
    private String name;

    @PostMapping(value = "/issue", params = "back")
    public String relBack() {
        return "redirect:/select";
    }

    @PostMapping(value = "/issue", params = "apollo")
    public String showApollo() {
        this.name = "apollo";
        return "redirect:/issue";
    }

    @PostMapping(value = "/issue", params = "httpie")
    public String showHttpie() {
        this.name = "httpie";
        return "redirect:/issue";
    }

    @GetMapping(value = "/issue")
    public String developer(/*@RequestParam(name = "name", required = true) String name,*/ Model model) {
        model.addAttribute("status", issueService.getIssueStatus(name));
        List<Double> list = issueService.getValues(name);
        model.addAttribute("v1", list.get(0));
        model.addAttribute("v2", list.get(1));
        model.addAttribute("v3", list.get(2));
        model.addAttribute("description", issueService.getDescriptionKey(name));
        if (name.equals("apollo"))
            model.addAttribute("picture", "apollo.png");
        else
            model.addAttribute("picture", "httpie.png");
        return "issue";
    }


    @GetMapping("/issue/statuslist")
    public JsonResult getStatus(@RequestParam(name = "name", required = true) String name) {
        List<Long> data = issueService.getIssueStatus(name);
        System.out.println(data);
        return JsonResult.buildSuccess(data);
    }

    @GetMapping("/issue/timevalues")
    public JsonResult getVals(@RequestParam(name = "name", required = true) String name) {
        List<Double> data = issueService.getValues(name);
        System.out.println(data);
        return JsonResult.buildSuccess(data);
    }

}
