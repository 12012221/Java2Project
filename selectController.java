package com.example.springproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class selectController {
    @PostMapping(value = "/select", params = "developer")
    public String chooseDeveloper() {//跳转到developer
        return "developer";
    }

    @PostMapping(value = "/select", params = "release")
    public String chooseRelease() {//跳转到release
        return "release";
    }

    @PostMapping(value = "/select", params = "issue")
    public String chooseIssue() {//跳转到issue
        return "issue";
    }

    @PostMapping(value = "/select", params = "commit")
    public String chooseCommit() {//跳转到issue
        return "commit";
    }
}
