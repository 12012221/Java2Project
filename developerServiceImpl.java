package com.example.springproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springproject.domain.Developer;
import com.example.springproject.mapper.developerMapper;
import com.example.springproject.service.developerService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class developerServiceImpl extends ServiceImpl<developerMapper, Developer> implements developerService {

    @Autowired
    private developerMapper developerMapper;

    @Override
    public List<Long> getTotal(String name) {
        List<Long> data = new ArrayList<>();
        List<Long> all = new ArrayList<>();

        Long tot = Long.valueOf(developerMapper.selectCount(new QueryWrapper<Developer>().eq("repo_name", name)));
        System.out.println(tot);
        data.add(tot);

        QueryWrapper<Developer> maxs = new QueryWrapper<Developer>().eq("repo_name", name)
                .select("commit_num").orderByDesc("commit_num");

        List<Map<String, Object>> maps = developerMapper.selectMaps(maxs);
        maps.forEach(mm -> {
            all.add(Long.parseLong((String.valueOf(mm.get("commit_num")))));
            if (data.size() < 6)
                data.add(Long.parseLong((String.valueOf(mm.get("commit_num")))));
        });

        long tot_commit = 0;
        for (Long aLong : all) {
            tot_commit += aLong;
        }

        long sum = 0;
        for (Long datum : data) {
            sum += datum;
        }
        data.add(tot_commit - sum + tot);

        return data; //tot top5 others
    }

    @Override
    public List<String> getInfo(String name) {
        List<String> data = new ArrayList<>();
        QueryWrapper<Developer> maxs = new QueryWrapper<Developer>().eq("repo_name", name)
                .select("id", "commit_num", "name").orderByDesc("commit_num").last("limit 5");

        List<Map<String, Object>> maps = developerMapper.selectMaps(maxs);
        maps.forEach(mm -> {
            String t1 = String.valueOf(mm.get("id"));
            String t2 = String.valueOf(mm.get("commit_num"));
            String t3 = String.valueOf(mm.get("name"));
            System.out.println(t1);
            System.out.println(t2);
            System.out.println(t3);
            String str = t1 + "-" + t2 + "-" + t3;
            data.add(str);
        });

        return data;
    }


}
