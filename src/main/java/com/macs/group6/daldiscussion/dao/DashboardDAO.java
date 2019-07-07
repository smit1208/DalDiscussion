package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("DashboardDAO")
public class DashboardDAO implements IDashboardDAO {

    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;

    private DatabaseConfig databaseConfig;
    private static final String GETPOSTBYUSERID = "{call getPostsByUserID(?)}";
    private static final String DELETEPOSTBYID = "{call deletePostById(?)}";

    @Autowired
    public DashboardDAO(@Qualifier("DatabaseConfig") DatabaseConfig databaseConfig){
        this.databaseConfig = databaseConfig;
    }
    @Override
    public Map<String,Object> getPostsByUserID(int user_id) {
        Map<String, Object> personalPostMap = new HashMap<>();

        try {

            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(GETPOSTBYUSERID);
            callableStatement.setInt(1,user_id);
            resultSet = callableStatement.executeQuery();
            List<Post> personalPosts = new ArrayList<>();
            while (resultSet.next()) {
                Post personalPost = new Post();
                personalPost.setId(resultSet.getInt("id"));
                personalPost.setUser_id(resultSet.getInt("user_id"));
                personalPost.setPost_title(resultSet.getString("post_title"));
                personalPost.setPost_description(resultSet.getString("post_desc"));
                personalPosts.add(personalPost);
            }
            personalPostMap.put("personalPosts", personalPosts);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return personalPostMap;
    }

    @Override
    public void deletePostById(int post_id) {
        try {
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(DELETEPOSTBYID);
            callableStatement.setInt(1,post_id);
            resultSet = callableStatement.executeQuery();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
    }
}

