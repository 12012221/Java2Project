package com.example.springproject.web;

import com.example.springproject.domain.Developer;
import com.example.springproject.domain.Issue;
import com.example.springproject.domain.Release;
import com.example.springproject.service.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestfulController {
    @Autowired
    private restfulDeveloper restfulDeveloper;
    @Autowired
    private restfulIssue restfulIssue;
    @Autowired
    private restfulRelease restfulRelease;

    @GetMapping("/developer/info")
    public List<Developer> getDeveloperInfo() {
        return restfulDeveloper.findAll();
    }

    @GetMapping("/developer/count")
    public long getDeveloperCount() {
        return restfulDeveloper.findCount();
    }

    @GetMapping("/issue/info")
    public List<Issue> getIssueInfo() {
        return restfulIssue.findAll();
    }

    @GetMapping("/issue/count")
    public long getIssueCount() {
        return restfulIssue.findCount();
    }

    @GetMapping("/release/info")
    public List<Release> getReleaseInfo() {
        return restfulRelease.findAll();
    }

    @GetMapping("/release/count")
    public long getRleaseCount() {
        return restfulRelease.findCount();
    }
}
