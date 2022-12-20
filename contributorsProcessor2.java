package com.example.springproject.github;

import com.example.springproject.domain.Developer;
import java.util.ArrayList;
import java.util.List;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

public class contributorsProcessor2 implements PageProcessor {
    @Override
    public void process(Page page) {
        String str = page.getHtml().regex("\\[.*\\]").toString();
        List<String> id = new JsonPathSelector("$.*.id").selectList(str);
        List<String> name = new JsonPathSelector("$.*.login").selectList(str);
        List<String> num = new JsonPathSelector("$.*.contributions").selectList(str);

        List<Developer> list = new ArrayList<>();
        for (int i = 0; i < id.size(); i++) {
            Developer developer = new Developer();
            developer.setId(Long.parseLong(id.get(i)));
            developer.setName(name.get(i));
            developer.setCommit_num(Integer.parseInt(num.get(i)));
            developer.setRepoName("httpie");
            list.add(developer);
        }

        page.putField("developers", list);
    }

    @Override
    public Site getSite() {
        return Site.me()
                .setDomain("blog.sina.com.cn")
                .addHeader("Authorization", "Bearer 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")
                .setUserAgent(
                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
                .setCharset("UTF-8");
    }

    public static void main(String[] args) {
        Spider.create(new contributorsProcessor2())
                .addUrl("https://api.github.com/repos/httpie/httpie/contributors?page=1&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/contributors?page=2&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/contributors?page=3&per_page=100")
                .addPipeline(new contributorsPipeline())
                .addPipeline(new ConsolePipeline())
                .start();
    }
}
