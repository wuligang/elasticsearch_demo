package com.elasticsearch.demo.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: demo
 * @description:
 * @author: wlg
 * @create: 2021-08-03 13:58:20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private String img;
    private String prices;
    private String title;
}
