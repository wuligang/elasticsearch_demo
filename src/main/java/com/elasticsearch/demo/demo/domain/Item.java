package com.elasticsearch.demo.demo.domain;

import io.searchbox.annotations.JestId;
import lombok.Data;
import lombok.ToString;

/**
 * @author wlg
 * @version V1.0
 * @Package com.elasticsearch.demo.demo.domain
 * @date 2019/11/22 0022 09:47
 * @Copyright © 2019-2020 河南讯众科技有限公司
 */
@Data
@ToString
//@Document(indexName = "item",type = "docs",shards = 1,replicas = 0)
public class Item {
    //    @Id
    @JestId
    private Long id;
    //    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String title; //标题
    //    @Field(type = FieldType.Keyword)
    private String category;// 分类
    //    @Field(type = FieldType.Keyword)
    private String brand; // 品牌
    //    @Field(type = FieldType.Double)
    private Double price; // 价格
    //    @Field(type = FieldType.Keyword,index = false)
    private String images; // 图片地址

    public Item() {
    }

    public Item(Long id, String title, String category, String brand, Double price, String images) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.images = images;
    }
}
