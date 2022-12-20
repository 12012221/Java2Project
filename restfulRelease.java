package com.example.springproject.service;

import com.example.springproject.domain.Developer;
import com.example.springproject.domain.Release;
import java.util.List;

public interface restfulRelease {
  List<Release> findAll();
  long findCount();
}
