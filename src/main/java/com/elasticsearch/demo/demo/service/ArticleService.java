package com.elasticsearch.demo.demo.service;

import com.elasticsearch.demo.demo.domain.Article;
import com.elasticsearch.demo.demo.util.MysqlJdbcFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wlg
 * @version V1.0
 * @Package com.elasticsearch.demo.demo.service
 * @date 2019/11/22 0022 16:58
 * @Copyright © 2019-2020 河南讯众科技有限公司
 */
@Data
@Component
@Slf4j
public class ArticleService {



    public Connection conn=null;
    public Statement st=null;
    public ResultSet rs=null;

    @Autowired
    MysqlJdbcFactory factory;

    public List<Article> getAllArticle() {
        List<Article> list=new ArrayList<Article>();
        try{
            String sql="select * from article";
            conn=factory.getConnection();
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                Article article=new Article();
                article.setTitleId(rs.getLong("titleId"));
                article.setTitle(rs.getString("title"));
                article.setAuthor(rs.getString("author"));
                article.setContent(rs.getString("content"));
                article.setPublishDate(rs.getString("publishDate"));
                list.add(article);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            factory.closeAll(conn, st, rs);
        }
        return list;
    }
}
