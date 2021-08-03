package com.elasticsearch.demo.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: demo
 * @description:
 * @author: wlg
 * @create: 2021-08-03 13:33:00
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private int id;
}
