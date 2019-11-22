package com.elasticsearch.demo.demo.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author wlg
 * @version V1.0
 * @Package com.elasticsearch.demo.demo.util
 * @date 2019/11/22 0022 16:56
 * @Copyright © 2019-2020 河南讯众科技有限公司
 */
@Slf4j
@Data
@Component
public class MysqlJdbcFactory {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://192.168.52.128:3308/article";
    private static String user = "root";
    private static String pwd = "123456";

    //连接数据库  mysql
    public static Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName(driver);
            conn= DriverManager.getConnection(url,user,pwd);
            log.info("[数据库连接成功...]");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[数据库连接失败...]", e.getMessage());
        }
        return conn;
    }

    //关闭数据库
    public static void closeAll(Connection conn, Statement st, ResultSet rs){
        try {
            if(rs!=null){
                rs.close();
            }
            if(st!=null){
                st.close();
            }
            if(conn!=null){
                conn.close();
            }
            log.info("[数据库关闭...]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
