package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Post;
import database.DatabaseConfig;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HomeDAO implements IHomeDao {
    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;
    private static final String GETALLPOST = "{call getAllPosts()}";

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();

        try {
            connection = DatabaseConfig.getInstance().loadDatabase();

            callableStatement = connection.prepareCall(GETALLPOST);
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setPost_title(resultSet.getString("post_title"));
                post.setPost_description(resultSet.getString("post_desc"));
                posts.add(post);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return posts;
    }
}
