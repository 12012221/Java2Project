package com.example.springproject.github;

import com.example.springproject.domain.Issue;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

public class issuesProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        String str = page.getRawText();
        List<String> id = new JsonPathSelector("$.*.id").selectList(str);
        List<String> create_time = new JsonPathSelector("$.*.created_at").selectList(str);
        List<String> closed_time = new JsonPathSelector("$.*.closed_at").selectList(str);
        List<String> title = new JsonPathSelector("$.*.title").selectList(str);
        List<String> state = new JsonPathSelector("$.*.state").selectList(str);
        List<String> description = new JsonPathSelector("$.*.labels[*].description").selectList(str);

        List<Issue> list = new ArrayList<>();
        for (int i = 0; i < id.size(); i++) {
            Issue issue = new Issue();
            issue.setId(Long.parseLong(id.get(i)));
            issue.setCreate_time(Timestamp.valueOf(create_time.get(i)
                    .replace("T", " ").replace("Z", "")));
            issue.setTitle(title.get(i));
            issue.setStatus(state.get(i));
            issue.setRepoName("apollo");
            if (Objects.equals(state.get(i), "closed"))
                issue.setClose_time(Timestamp.valueOf(closed_time.get(i)
                        .replace("T", " ").replace("Z", "")));
            if (i < description.size())
                issue.setDescription(description.get(i));
            list.add(issue);
        }

        page.putField("issues", list);
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
        Spider.create(new issuesProcessor())
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=open&page=1&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=open&page=2&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=1&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=2&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=3&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=4&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=5&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=6&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=7&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=8&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=9&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=10&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=11&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=12&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=13&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=14&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=15&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=16&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=17&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=18&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=19&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=20&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=21&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=22&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=23&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=24&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=25&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=26&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=27&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=28&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=29&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=30&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=31&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=32&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=33&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=34&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=35&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=36&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=37&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=38&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=39&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=40&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=41&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=42&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=43&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=44&per_page=100")
                .addUrl("https://api.github.com/repos/apolloconfig/apollo/issues?state=closed&page=45&per_page=100")
                .addPipeline(new issuesPipeline())
                .addPipeline(new ConsolePipeline())
                .start();
    }
}
