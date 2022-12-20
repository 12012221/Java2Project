package com.example.springproject.service.impl;

import com.example.springproject.domain.Repo;
import com.example.springproject.service.RepoService;
import org.springframework.stereotype.Service;


@Service
public class RepoServiceImpl implements RepoService {

    @Override
    public Repo findInfo() {
        return new Repo();
    }
}
