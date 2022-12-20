package com.example.springproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springproject.domain.Commit;
import com.example.springproject.domain.Release;
import com.example.springproject.mapper.commitMapper;
import com.example.springproject.mapper.releaseMapper;
import com.example.springproject.service.releaseService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class releaseServiceImpl extends ServiceImpl<releaseMapper, Release> implements releaseService {
    @Autowired
    private releaseMapper releaseMapper;
    @Autowired
    private commitMapper commitMapper;

    @Override
    public List<Long> getTotal(String name) {
        List<Long> data = new ArrayList<>();
        Long tot = Long.valueOf(releaseMapper.selectCount(new QueryWrapper<Release>().eq("repo_name", name)));

        System.out.println(tot);

        data.add(tot);

        return data;
    }

    @Override
    public List<String> getInfo(String name) {
        List<String> data = new ArrayList<>();

        QueryWrapper<Release> release_dates = new QueryWrapper<Release>().eq("repo_name", name)
                .select("distinct publish_time").orderByDesc("publish_time");
        List<Map<String, Object>> release_maps = releaseMapper.selectMaps(release_dates);

//        QueryWrapper<Commit> commit_dates = new QueryWrapper<Commit>().eq("repo_name", name)
//                .select("id", "time").orderByDesc("time");
//        List<Map<String, Object>> commit_maps = commitMapper.selectMaps(commit_dates);

        for (int i = 0; i < release_maps.size() - 1; i++) {
            Date date1 = (Date) release_maps.get(i).get("publish_time");
            Date date2 = (Date) release_maps.get(i + 1).get("publish_time");
            Long tot = Long.valueOf(commitMapper.selectCount(new QueryWrapper<Commit>()
                    .eq("repo_name", name)
                    .le("time", date1)
                    .ge("time", date2)));
            String str = tot + "=" + date1.toString() + "=" + date2.toString();
            data.add(str);
        }

        data.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (Integer.parseInt(o2.split("=")[0]) > Integer.parseInt(o1.split("=")[0]))
                    return 1;
                else if (Integer.parseInt(o2.split("=")[0]) == Integer.parseInt(o1.split("=")[0]))
                    return 0;
                else return -1;
            }
        });
        System.out.println(data);

        return data;
    }

    @Override
    public List<Long> getDistribution(String name) {
        List<Long> data = new ArrayList<>();

        Long tot1 = Long.valueOf(commitMapper.selectCount(new QueryWrapper<Commit>()
                .eq("repo_name", name)
                .le("time", Date.valueOf("2022-12-31"))
                .ge("time", Date.valueOf("2022-01-01"))));
        System.out.println(tot1);
        data.add(tot1);

        Long tot2 = Long.valueOf(commitMapper.selectCount(new QueryWrapper<Commit>()
                .eq("repo_name", name)
                .le("time", Date.valueOf("2021-12-31"))
                .ge("time", Date.valueOf("2021-01-01"))));
        System.out.println(tot2);
        data.add(tot2);

        Long tot3 = Long.valueOf(commitMapper.selectCount(new QueryWrapper<Commit>()
                .eq("repo_name", name)
                .le("time", Date.valueOf("2020-12-31"))
                .ge("time", Date.valueOf("2020-01-01"))));
        System.out.println(tot3);
        data.add(tot3);

        Long tot4 = Long.valueOf(commitMapper.selectCount(new QueryWrapper<Commit>()
                .eq("repo_name", name)
                .le("time", Date.valueOf("2019-12-31"))
                .ge("time", Date.valueOf("2019-01-01"))));
        System.out.println(tot4);
        data.add(tot4);

        Long tot5 = Long.valueOf(commitMapper.selectCount(new QueryWrapper<Commit>()
                .eq("repo_name", name)
                .le("time", Date.valueOf("2018-12-31"))
                .ge("time", Date.valueOf("2018-01-01"))));
        System.out.println(tot5);
        data.add(tot5);

        Long tot6 = Long.valueOf(commitMapper.selectCount(new QueryWrapper<Commit>()
                .eq("repo_name", name)
                .le("time", Date.valueOf("2017-12-31"))
                .ge("time", Date.valueOf("2017-01-01"))));
        System.out.println(tot6);
        data.add(tot6);

        Long tot7 = Long.valueOf(commitMapper.selectCount(new QueryWrapper<Commit>()
                .eq("repo_name", name)
                .le("time", Date.valueOf("2016-12-31"))
                .ge("time", Date.valueOf("2016-01-01"))));
        System.out.println(tot7);
        data.add(tot7);

        Long tot8 = Long.valueOf(commitMapper.selectCount(new QueryWrapper<Commit>()
                .eq("repo_name", name)
                .le("time", Date.valueOf("2015-12-31"))
                .ge("time", Date.valueOf("2015-01-01"))));
        System.out.println(tot8);
        data.add(tot8);

        Long tot9 = Long.valueOf(commitMapper.selectCount(new QueryWrapper<Commit>()
                .eq("repo_name", name)
                .le("time", Date.valueOf("2014-12-31"))
                .ge("time", Date.valueOf("2014-01-01"))));
        System.out.println(tot9);
        data.add(tot9);

        Long tot10 = Long.valueOf(commitMapper.selectCount(new QueryWrapper<Commit>()
                .eq("repo_name", name)
                .le("time", Date.valueOf("2013-12-31"))
                .ge("time", Date.valueOf("2013-01-01"))));
        System.out.println(tot10);
        data.add(tot10);

        Long tot11 = Long.valueOf(commitMapper.selectCount(new QueryWrapper<Commit>()
                .eq("repo_name", name)
                .le("time", Date.valueOf("2012-12-31"))
                .ge("time", Date.valueOf("2012-01-01"))));
        System.out.println(tot11);
        data.add(tot11);

        return data;
    }
}
