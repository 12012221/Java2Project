package com.example.springproject.github;

import com.example.springproject.domain.Release;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

public class releasesProcessor2 implements PageProcessor {
    @Override
    public void process(Page page) {
        String str = page.getHtml().regex("\\[.*\\]").toString();
        List<String> id = new JsonPathSelector("$.*.id").selectList(str);
        List<String> published_at = new JsonPathSelector("$.*.published_at").selectList(str);

        List<Release> list = new ArrayList<>();
        for (int i = 0; i < id.size(); i++) {
            Release release = new Release();
            release.setId(Long.parseLong(id.get(i)));
            release.setPublish_time(Date.valueOf(published_at.get(i).substring(0, 10)));
            release.setRepoName("httpie");
            list.add(release);
        }

        page.putField("releases", list);
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
        Spider.create(new releasesProcessor2())
                .addUrl("https://api.github.com/repos/httpie/httpie/releases?page=1&per_page=100")
                .addPipeline(new releasesPipeline())
                .addPipeline(new ConsolePipeline())
                .start();
    }
}
