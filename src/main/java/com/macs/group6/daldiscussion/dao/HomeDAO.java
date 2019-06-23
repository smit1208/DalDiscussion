package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Post;
import database.DatabaseConfig;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeDAO implements IHomeDAO {
    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;

    private static IHomeDAO iHomeDAO;

    private static final String GETALLPOST = "{call getAllPosts()}";

    @Override
    public Map<String,Object> getAllPosts() {
        Map<String,Object> postMap = new HashMap<>();

        try {
            connection = DatabaseConfig.getInstance().loadDatabase();

            callableStatement = connection.prepareCall(GETALLPOST);
            resultSet = callableStatement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setPost_title(resultSet.getString("post_title"));
                post.setPost_description(resultSet.getString("post_desc"));
                posts.add(post);
            }
            postMap.put("posts",posts);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return postMap;
    }
    public static IHomeDAO getInstance(){
        if(iHomeDAO == null){
            iHomeDAO = new HomeDAO();
        }
        return iHomeDAO;
    }
}
