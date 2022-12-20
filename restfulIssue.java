package com.example.springproject.service;

import com.example.springproject.domain.Developer;
import com.example.springproject.domain.Issue;
import java.util.List;

public interface restfulIssue {
  List<Issue> findAll();
  long findCount();
}
