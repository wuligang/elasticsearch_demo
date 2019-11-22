package com.elasticsearch.demo.demo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wlg
 * @version V1.0
 * @Package com.elasticsearch.demo.demo.domain
 * @date 2019/11/22 0022 16:55
 * @Copyright © 2019-2020 河南讯众科技有限公司
 */

@Data
public class Article implements Serializable {

    private Long titleId;
    private String title;
    private String author;
    private String content;
    private String publishDate;

    @Override
    public String toString() {
        return "Article{" +
                "titleId=" + titleId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }
}