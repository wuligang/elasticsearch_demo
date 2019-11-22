package com.elasticsearch.demo.demo.domain;

import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author wlg
 * @version V1.0
 * @Package com.elasticsearch.demo.demo.domain
 * @date 2019/11/22 0022 10:01
 * @Copyright © 2019-2020 河南讯众科技有限公司
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemTest {
//    @Autowired
//    ElasticsearchTemplate elasticsearchTemplate;
//
//    @Autowired
//    ItemRepository itemRepository;

    @Autowired
    private JestClient jestClient;

    @Test
    public void test1() {
        Index index = new Index.Builder(new Item(5L, "特价了吗", "手机", "", 20.0, "sssss")).index("idx_item2").type("item3").build();
        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testSearch(String searchContent) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.queryStringQuery(searchContent));
        //searchSourceBuilder.field("name");
        //searchSourceBuilder.query(QueryBuilders.matchQuery("name",searchContent));
        searchSourceBuilder.from(0).size(2);
        //构建搜索功能
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("idx_item")
                .addType("item")
                .build();

        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void exeSearch(){
        String search = "手机";
        testSearch(search);
    }
}