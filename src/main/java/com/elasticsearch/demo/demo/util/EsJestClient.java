package com.elasticsearch.demo.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author wlg
 * @version V1.0
 * @Package com.elasticsearch.demo.demo.util
 * @date 2019/11/22 0022 17:00
 * @Copyright © 2019-2020 河南讯众科技有限公司
 */
@Slf4j
@Component
public class EsJestClient {

    @Autowired
    private JestClient esClient;


    public static final String INDEX = "es-article";
    public static final String TYPE = "articles";

    /**
     * 创建所引
     *
     * @throws IOException
     */
    public void createIndex() throws IOException {
        Settings.Builder builder = Settings.builder();
        builder.put("number_of_shards", 5);
        builder.put("number_of_replicas", 1);

        //创建索引
        CreateIndex createIndex = new CreateIndex.Builder(INDEX)
                .settings(builder.build().getAsMap())
                .build();
        JestResult result = esClient.execute(createIndex);
        log.info("【创建索引:{}...】", INDEX);
        checkArgument(result.isSucceeded(), result.getErrorMessage());
        createMapping();
    }

    /**
     * 删除索引
     *
     * @param index
     * @throws IOException
     */
    public void deleteIndex(String index) throws IOException {
        DeleteIndex deleteIndex = new DeleteIndex.Builder(index).build();
        JestResult result = esClient.execute(deleteIndex);
        log.info("【删除索引:{}...】", index);
        checkArgument(result.isSucceeded(), result.getErrorMessage());
    }

    /**
     * put映射
     *
     * @throws IOException
     */
    public void createMapping() throws IOException {

        JSONObject objSource = new JSONObject().fluentPut("properties", new JSONObject()
                .fluentPut("title", new JSONObject()
                        .fluentPut("type", "text")
                )
                .fluentPut("author", new JSONObject()
                        .fluentPut("type", "text")
                )
                .fluentPut("content", new JSONObject()
                        .fluentPut("type", "text")
                )
                .fluentPut("publishDate", new JSONObject()
                        .fluentPut("type", "date")
                )
        );
        PutMapping putMapping = new PutMapping.Builder(INDEX, TYPE, objSource.toJSONString()).build();
        JestResult result = esClient.execute(putMapping);

        log.info("【创建mapping映射成功...】");
        checkArgument(result.isSucceeded(), result.getErrorMessage());

    }

    /**
     * 向es中插入数据
     *
     * @param source
     * @param id
     * @throws Exception
     */
    public void putSource(Object source, String id) throws Exception {
        JestResult result = esClient.execute(
                new Index.Builder(source)
                        .index(INDEX)
                        .type(TYPE)
                        .id(id)
                        .refresh(true)
                        .build());
        checkArgument(result.isSucceeded(), result.getErrorMessage());
    }

    /**
     * 根据条件查询
     *
     * @param query
     * @return
     * @throws IOException
     */
    public SearchResult searchWithQuery(String query) throws IOException {
        log.info(">>>>查询条件：\\\r query:{}", query);
        Search search = new Search.Builder(query)
                .addIndex(INDEX)
                .addType(TYPE)
                .build();
        SearchResult result = esClient.execute(search);
        checkArgument(result.isSucceeded(), result.getErrorMessage());
        return result;
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public JestResult searchById(long id) throws Exception {
        Get build = new Get.Builder(INDEX, String.valueOf(id))
                .index(INDEX)
                .type(TYPE)
                .build();
        JestResult result = esClient.execute(build);
        return result;
    }


    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean delete(Long id) throws Exception {
        Delete build = new Delete.Builder(String.valueOf(id))
                .index(INDEX)
                .type(TYPE)
                .build();
        JestResult result = esClient.execute(build);
        checkArgument(result.isSucceeded(), result.getErrorMessage());
        return result.isSucceeded();

    }


    /**
     * 获取index下的映射
     *
     * @return
     * @throws Exception
     */
    public String getMapping() throws Exception {
        GetMapping build = new GetMapping.Builder().addIndex(INDEX).addType(TYPE).build();
        JestResult result = esClient.execute(build);
        checkArgument(result.isSucceeded(), result.getErrorMessage());
        return result.getSourceAsObject(JsonObject.class).toString();
    }


}
