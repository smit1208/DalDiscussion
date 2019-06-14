package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Post;
import database.DatabaseConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HomeDAO{
    public List<Post> getAllPosts(){
        List<Post> posts = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            DatabaseConfig databaseConfig = new DatabaseConfig();
            connection = databaseConfig.loadDatabase();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM post;");

            while (resultSet.next()){
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setPost_title(resultSet.getString("post_title"));
                post.setPost_description(resultSet.getString("post_desc"));
                posts.add(post);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(statement!=null){
                    statement.close();
                }
                if(resultSet!=null){
                    resultSet.close();
                }
                if(connection!=null){
                    connection.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return posts;
    }
}
