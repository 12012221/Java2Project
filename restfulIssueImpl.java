package com.example.springproject.service.impl;

import com.example.springproject.domain.Developer;
import com.example.springproject.domain.Issue;
import com.example.springproject.mapper.issueRepository;
import com.example.springproject.service.restfulIssue;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class restfulIssueImpl implements restfulIssue {
  @Autowired
  private issueRepository issueRepository;
  @Override
  public List<Issue> findAll() {
    return issueRepository.findAll();
  }

  @Override
  public long findCount() {
    return issueRepository.count();
  }
}
