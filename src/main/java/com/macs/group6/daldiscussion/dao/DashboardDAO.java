package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Post;
import org.apache.log4j.Logger;
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

/**
 * @author Vivek Shah
 */

@Component("DashboardDAO")
public class DashboardDAO implements IDashboardDAO {
    private static final Logger logger = Logger.getLogger(DashboardDAO.class);
    private static DashboardDAO instance;
    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;

    private DatabaseConfig databaseConfig;
    private static final String GETPOSTBYUSERID = "{call getPostsByUserID(?)}";
    private static final String DELETEPOSTBYID = "{call deletePostById(?)}";
    private static final String UPDATEPOSTBYID = "{call updatePostById(?,?,?)}";

    @Autowired
    public DashboardDAO(@Qualifier("DatabaseConfig") DatabaseConfig databaseConfig){
        this.databaseConfig = databaseConfig;
    }
    @Override
    public Map<String,Object> getPostsByUserID(int user_id) throws DAOException {
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
        } catch (SQLException e) {
            throw  new DAOException("<DashboardDAO>"+user_id+" - GET POST - ERROR", e);
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return personalPostMap;
    }

    @Override
    public void deletePostById(int post_id) throws DAOException {
        try {
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(DELETEPOSTBYID);
            callableStatement.setInt(1,post_id);
            resultSet = callableStatement.executeQuery();

        } catch (SQLException e) {
            throw  new DAOException("<DashboardDAO>"+post_id+" - DELETE POST - ERROR", e);
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
    }

    @Override
    public void updatePostById(String post_title, String post_description, int id) throws DAOException {
        try {
            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(UPDATEPOSTBYID);
            callableStatement.setString(1,post_title);
            callableStatement.setString(2,post_description);
            callableStatement.setInt(3,id);
            resultSet = callableStatement.executeQuery();

        } catch (SQLException e) {
            throw  new DAOException("<DashboardDAO>"+id+" - UPDATE POST - ERROR", e);
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
    }

}

