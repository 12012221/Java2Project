package com.example.springproject.web;

import com.example.springproject.json.JsonResult;
import com.example.springproject.service.releaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class releaseController {
    @Autowired
    private releaseService releaseService;
    private String name;

    @PostMapping(value = "/release", params = "back")
    public String relBack() {
        return "redirect:/select";//release跳转回select
    }


    @PostMapping(value = "/release", params = "apollo")
    public String showApollo() {
        this.name = "apollo";
        return "redirect:/release";
    }

    @PostMapping(value = "/release", params = "httpie")
    public String showHttpie() {
        this.name = "httpie";
        return "redirect:/release";
    }

    @GetMapping("/release")
    public String release(/*@RequestParam(name = "name", required = true) String name,*/ Model model) {
        model.addAttribute("total", releaseService.getTotal(name));
        model.addAttribute("info", releaseService.getInfo(name));
        model.addAttribute("dis", releaseService.getDistribution(name));
        return "release";
    }

    @GetMapping(value = "/release/total")
    public JsonResult getTotal(@RequestParam(name = "name", required = true) String name) {
        List<Long> data = releaseService.getTotal(name);
        System.out.println(data);
        return JsonResult.buildSuccess(data);
    }

    @GetMapping("release/info")
    public JsonResult getInfo(@RequestParam(name = "name", required = true) String name) {
        List<String> data = releaseService.getInfo(name);
        System.out.println(data);
        return JsonResult.buildSuccess(data);
    }

    @GetMapping("/release/distribution")
    public JsonResult getDis(@RequestParam(name = "name", required = true) String name) {
        List<Long> data = releaseService.getDistribution(name);
        System.out.println(data);
        return JsonResult.buildSuccess(data);
    }

}
