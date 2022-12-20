package com.example.springproject.web;

import com.example.springproject.json.JsonResult;
import com.example.springproject.service.developerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class developerController {
    @Autowired
    private developerService developerService;
    private String name;

    @PostMapping(value = "/developer", params = "back")
    public String relBack() {
        return "redirect:/select";
    }

    @PostMapping(value = "/developer", params = "apollo")
    public String showApollo() {
        this.name = "apollo";
        return "redirect:/developer";
    }

    @PostMapping(value = "/developer", params = "httpie")
    public String showHttpie() {
        this.name = "httpie";
        return "redirect:/developer";
    }

    @GetMapping("/developer")
    public String developer(/*@RequestParam(name = "name", required = true) String name,*/ Model model) {

        System.out.println("show");
        model.addAttribute("total", developerService.getTotal(name));
        model.addAttribute("info", developerService.getInfo(name));
        return "developer";
    }

    @GetMapping("/developer/total")
    public JsonResult getTotal(@RequestParam(name = "name", required = true) String name) {
        List<Long> data = developerService.getTotal(name);
        System.out.println(data);
        return JsonResult.buildSuccess(data);
    }

    @GetMapping("/developer/info")
    public JsonResult getInfo(@RequestParam(name = "name", required = true) String name) {
        List<String> data = developerService.getInfo(name);
        System.out.println(data);
        return JsonResult.buildSuccess(data);
    }

}
