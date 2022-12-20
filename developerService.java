package com.example.springproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springproject.domain.Developer;
import java.util.List;

public interface developerService extends IService<Developer> {
    public List<Long> getTotal(String name);

    public List<String> getInfo(String name);
}
