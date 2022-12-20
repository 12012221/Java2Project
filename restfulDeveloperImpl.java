package com.example.springproject.service.impl;

import com.example.springproject.domain.Developer;
import com.example.springproject.mapper.developerRepository;
import com.example.springproject.service.restfulDeveloper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class restfulDeveloperImpl implements restfulDeveloper {
  @Autowired
  private developerRepository developerRepository;

  @Override
  public List<Developer> findAll() {
    return developerRepository.findAll();
  }

  @Override
  public long findCount() {
    return developerRepository.count();
  }
}
