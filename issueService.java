package com.example.springproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springproject.domain.Issue;
import java.util.List;

public interface issueService extends IService<Issue> {

    public List<Long> getIssueStatus(String name);

    public List<Double> getValues(String name);

    public List<String> getDescriptionKey(String name);

}
