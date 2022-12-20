package com.example.springproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springproject.domain.Release;
import java.util.List;

public interface releaseService extends IService<Release> {

    public List<Long> getTotal(String name);

    public List<String> getInfo(String name);

    public List<Long> getDistribution(String name);
}
