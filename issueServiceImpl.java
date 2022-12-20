package com.example.springproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springproject.domain.Issue;
import com.example.springproject.mapper.issueMapper;
import com.example.springproject.service.issueService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class issueServiceImpl extends ServiceImpl<issueMapper, Issue> implements issueService {
    @Autowired
    private issueMapper issueMapper;

    @Override
    public List<Long> getIssueStatus(String name) {
        List<Long> data = new ArrayList<>();
        Long open = Long.valueOf(issueMapper.selectCount(new QueryWrapper<Issue>()
                .eq("repo_name", name).eq("status", "open")));
        Long close = Long.valueOf(issueMapper.selectCount(new QueryWrapper<Issue>()
                .eq("repo_name", name).eq("status", "closed")));

        System.out.println(open);
        System.out.println(close);

        data.add(open);
        data.add(close);

        return data;
    }

    @Override
    public List<Double> getValues(String name) {
        List<Double> data = new ArrayList<>();
        List<Double> tps = new ArrayList<>();

        QueryWrapper<Issue> times = new QueryWrapper<Issue>()
                .select("create_time", "close_time").eq("repo_name", name)
                .eq("status", "closed");

        List<Map<String, Object>> maps = issueMapper.selectMaps(times);
        maps.forEach(mm -> {
            Timestamp t1 = (Timestamp) mm.get("create_time");
            Timestamp t2 = (Timestamp) mm.get("close_time");
            System.out.println(t1);
            System.out.println(t2);
            long second = (t2.getTime() - t1.getTime()) / 1000;

            double hor = (double) second / 3600;
            tps.add(hor);
        });

        double tot = 0;
        double var = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < tps.size(); i++) {
            System.out.println(tps.get(i));
            if (max < tps.get(i))
                max = tps.get(i);
            if (min > tps.get(i))
                min = tps.get(i);
            tot += tps.get(i);
        }
        for (int i = 0; i < tps.size(); i++) {
            var += (tps.get(i) - tot / tps.size()) * (tps.get(i) - tot / tps.size());
        }
        System.out.println(tot);
        System.out.println(tps.size());
        data.add(tot / tps.size());
        data.add(max - min);
        data.add(var / tps.size());
        System.out.println(data);
        return data;
    }

    @Override
    public List<String> getDescriptionKey(String name) {
        List<String> data = new ArrayList<>();

        QueryWrapper<Issue> times = new QueryWrapper<Issue>().eq("repo_name", name)
                .select("description", "count(description) cc").groupBy("description")
                .orderByDesc("cc");
        List<Map<String, Object>> maps = issueMapper.selectMaps(times);

        maps.forEach(mm -> {
            String des = String.valueOf(mm.get("description"));
            long num = Long.parseLong(String.valueOf(mm.get("cc")));

            if (!Objects.equals(des, "null") && des != null && !des.equals(""))
                data.add(des + "=" + num);
        });

        return data;
    }

//    @Override
//    public List<String> getTitleKey(String name) {
//        return null;
//    }
}
