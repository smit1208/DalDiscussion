package com.macs.group6.daldiscussion.dao.impl;

import com.macs.group6.daldiscussion.dao.PostDao;
import com.macs.group6.daldiscussion.model.Post;
import database.DatabaseConfig;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class PostDaoImpl implements PostDao {
    @Override
    public void create(Post post) {
        Connection connection = null;
        Statement statement = null;


        try{
            DatabaseConfig databaseConfig = new DatabaseConfig();
            connection = databaseConfig.loadDatabase();
            statement = connection.createStatement();
            String query = "insert into post(post_title, post_desc, user_id, category) values('"+post.getPost_title()+"','"+
                    post.getPost_description()+"',1,1);";
            System.out.println(query);
            int result = statement.executeUpdate(query);
            System.out.println(result);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(statement!=null){
                    statement.close();
                }
                if(connection!=null){
                    connection.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}
