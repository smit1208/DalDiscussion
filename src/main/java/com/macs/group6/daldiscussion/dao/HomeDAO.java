package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import database.DatabaseConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HomeDAO{
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public List<Post> getAllPosts(){
        List<Post> posts = new ArrayList<>();

        try{
            DatabaseConfig.getInstance();
            connection = DatabaseConfig.getInstance().loadDatabase();
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
            DatabaseConfig.getInstance().closeConnection(connection,statement,resultSet);
        }
        return posts;
    }
    public List<Comment> getComments(int postId){
        List<Comment> commentList = new ArrayList<>();
        try{
            connection = DatabaseConfig.getInstance().loadDatabase();
            statement = connection.createStatement();
            String query = "SELECT * FROM comments where post_id ="+postId;
            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setComment_description(resultSet.getString("comment_body"));
                commentList.add(comment);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,statement,resultSet);
        }
    return commentList;
    }

}
