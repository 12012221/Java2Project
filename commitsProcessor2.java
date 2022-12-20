package com.example.springproject.github;

import com.example.springproject.domain.Commit;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

public class commitsProcessor2 implements PageProcessor {
    @Override
    public void process(Page page) {
        String str = page.getHtml().regex("\\[.*\\]").toString();
//        List<String> id=new JsonPathSelector("$.*.author.id").selectList(str);
        List<String> date = new JsonPathSelector("$.*.commit.author.date").selectList(str);

        List<Commit> list = new ArrayList<>();
        for (int i = 0; i < date.size(); i++) {
            Commit commit = new Commit();
//            commit.setId(Long.parseLong(id.get(i)));
            commit.setTime(Date.valueOf(date.get(i).substring(0, 10)));
            commit.setRepoName("httpie");
            list.add(commit);
        }

        page.putField("commits", list);
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
        Spider.create(new commitsProcessor2())
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=1&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=2&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=3&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=4&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=5&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=6&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=7&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=8&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=9&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=10&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=11&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=12&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=13&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=14&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=15&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=16&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=17&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=18&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=19&per_page=100")
                .addUrl("https://api.github.com/repos/httpie/httpie/commits?page=20&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=30&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=31&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=32&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=33&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=34&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=35&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=36&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=37&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=38&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=39&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=40&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=41&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=42&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=43&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=44&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=45&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=46&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=47&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=48&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=49&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=50&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=51&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=52&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=53&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=54&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=55&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=56&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=57&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=58&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=59&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=60&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=61&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=62&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=63&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=64&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=65&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=66&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=67&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=68&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=69&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=70&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=71&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=72&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=73&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=74&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=75&per_page=100")
//                .addUrl("https://api.github.com/repos/keras-team/keras/commits?page=76&per_page=100")
                .addPipeline(new commitsPipeline())
                .addPipeline(new ConsolePipeline())
                .start();
    }
}
