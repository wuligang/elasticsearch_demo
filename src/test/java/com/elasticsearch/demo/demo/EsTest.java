package com.elasticsearch.demo.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.elasticsearch.demo.demo.domain.Article;
import com.elasticsearch.demo.demo.service.ArticleService;
import com.elasticsearch.demo.demo.util.EsJestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

/**
 * @author wlg
 * @version V1.0
 * @Package com.elasticsearch.demo.demo
 * @date 2019/11/22 0022 17:03
 * @Copyright © 2019-2020 河南讯众科技有限公司
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {

    @Autowired
    EsJestClient esClient;

    @Autowired
    ArticleService articleService;


    /**
     * 初始化索引并创建映射
     */
    @Test
    public void init(){
        try {
            esClient.deleteIndex("es-article");
            esClient.createIndex();
        }catch (IOException e){
            e.printStackTrace();
            log.error("【es-article索引建立失败...】", e.getMessage());
        }
    }

    /**
     * 获取映射信息
     */
    @Test
    public void getMappingTest(){
        try {
            String mapping = esClient.getMapping();
            log.error("【获取映射信息:{}】", mapping);
        }catch (Exception e){
            e.printStackTrace();
            log.error("【获取映射信息失败...】", e.getMessage());
        }
    }

    /**
     * 添加数据
     */
    @Test
    public void putDataTest(){

        try{
            List<Article> list = articleService.getAllArticle();
            for (Article article: list) {
                esClient.putSource(article, article.getTitleId().toString());
                log.info("【《{}》保存成功...】", article.getTitle());
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error("【数据保存失败...】", e.getMessage());
        }

    }


    /**
     * 查询type下的所有数据
     */
    @Test
    public void searchType(){

        JSONObject resultJson = new JSONObject();
        JSONArray retJsonArray = new JSONArray();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.sort("publishDate", SortOrder.ASC); //根据发版日期排序
        //搜索关键词
        searchSourceBuilder.query(QueryBuilders.queryStringQuery("优惠"));
        //查看前两条
        searchSourceBuilder.from(0).size(2);
        SearchResult result;
        try {
            result = esClient.searchWithQuery(searchSourceBuilder.toString());
            for(SearchResult.Hit<JSONObject, Void> item : result.getHits(JSONObject.class)) {
                retJsonArray.add(item.source);
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("【查询数据失败...】", e.getMessage());
        }
        resultJson.fluentPut("data", retJsonArray);
        log.info("【result:{}】", resultJson.toString());

    }

    /**
     * 根据条件查询并设置高亮显示内容
     */
    @Test
    public void searchByTj() {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.queryStringQuery(MessageFormat.format("title:{0} OR content:{0}", "作品")));
            searchSourceBuilder.sort("publishDate", SortOrder.DESC);
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("title");
            highlightBuilder.field("content");
            highlightBuilder.preTags("<em>").postTags("</em>");//高亮标签
            highlightBuilder.fragmentSize(100);
            searchSourceBuilder.highlighter(highlightBuilder);
            SearchResult result = esClient.searchWithQuery(searchSourceBuilder.toString());
            for (SearchResult.Hit<JSONObject, Void> item : result.getHits(JSONObject.class)){
                if (item.highlight.containsKey("title")) {
                    item.source.fluentPut("highlight", item.highlight.get("title").get(0));
                }
                if (item.highlight.containsKey("content")) {
                    item.source.fluentPut("highlight_content", item.highlight.get("content").get(0));
                }
                item.source.remove("content");
                log.info("sss:{}", JSONArray.toJSON(item).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【查询数据失败...】", e.getMessage());
        }
    }


    /**
     * 根据id查询
     */
    @Test
    public void searchByIdTest() {
        try {
            JestResult jestResult = esClient.searchById(1L);
            Article article = jestResult.getSourceAsObject(Article.class);
            log.info("【《{}》查询成功,{}...】", article.getTitle(), article);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【查询数据失败...】", e.getMessage());
        }
    }


    /**
     * 删除数据
     */
    @Test
    public void deleteTest() {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            SearchResult  result = esClient.searchWithQuery(searchSourceBuilder.toString());
            for (SearchResult.Hit<JSONObject, Void> item : result.getHits(JSONObject.class)) {
                esClient.delete(item.source.getLong("titleId"));
                log.info("【《{}》删除成功...】", item.source.getString("title"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【删除数据失败...】", e.getMessage());
        }
    }


}
