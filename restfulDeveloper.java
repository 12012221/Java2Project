package com.example.springproject.service;

import com.example.springproject.domain.Developer;
import org.springframework.stereotype.Service;
import java.util.List;

public interface restfulDeveloper {
  List<Developer> findAll();
  long findCount();
}
