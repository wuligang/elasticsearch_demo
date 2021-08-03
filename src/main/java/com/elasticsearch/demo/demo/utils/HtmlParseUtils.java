package com.elasticsearch.demo.demo.utils;

import com.elasticsearch.demo.demo.domain.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: wlg
 * @create: 2021-08-03 13:54:52
 **/
@Component
public class HtmlParseUtils {
    /**
     * 爬取京东商城搜索数据
     * @param keyWork
     * @return
     * @throws IOException
     */
    public List<Content> listGoods(String keyWork) throws IOException {
        String url = "https://search.jd.com/Search?keyword="+keyWork;
        Document document = Jsoup.parse(new URL(url),30000);
        Element element = document.getElementById("J_goodsList");
        Elements li = element.getElementsByTag("li");
        List<Content> list = new ArrayList<>();
        for (Element elements : li) {
            String img = elements.getElementsByTag("img").eq(0).attr("src");
            String price = elements.getElementsByClass("p-price").eq(0).text();
            String title = elements.getElementsByClass("p-name").eq(0).text();
            Content content = new Content();
            content.setImg(img);
            content.setPrices(price);
            content.setTitle(title);
            list.add(content);
        }
        return list;
    }
}
