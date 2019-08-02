package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.ReportedPost;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("HomeDAO")
public class HomeDAO implements IHomeDAO {
    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;

    private DatabaseConfig databaseConfig = DatabaseConfig.getInstance();
    private static final String GETALLPOST = "{call getAllPosts()}";
    private static final String ADDREPORTINGPOST = "{call addReportingPost(?,?)}";
    private static final String FETCHREPORTEDPOSTBYUSERID = "{call fetchreportedPostsByUserID(?)}";
    private static final String GETSEARCHPOST = "{call getSearchPost(?)}";
    private static final String GETPOSTBYGROUPID = "{call getPostsByGroupId(?)}";


    @Override
    public Map<String, Object> getAllPosts() throws DAOException {
        Map<String, Object> postMap = new HashMap<>();

        try {
            connection = this.databaseConfig.loadDatabase();
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
            postMap.put("posts", posts);
        } catch (SQLException e) {
            throw  new DAOException("<HomeDAO> - GET ALL POSTS - ERROR", e);
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return postMap;
    }

    @Override
    public void addReportingPost(int user_id, int post_id) throws DAOException {
        try {
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(ADDREPORTINGPOST);
            callableStatement.setInt(1, user_id);
            callableStatement.setInt(2, post_id);
            resultSet = callableStatement.executeQuery();
        } catch (SQLException e) {
            throw  new DAOException("<HomeDAO> - ADD REPORTED POST - ERROR", e);
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
    }

    @Override
    public List<ReportedPost> fetchReportedPostByUserId(int reportedUser_id) throws DAOException {
        List<ReportedPost> reportedPosts = new ArrayList<>();
        try {
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(FETCHREPORTEDPOSTBYUSERID);
            callableStatement.setInt(1, reportedUser_id);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                ReportedPost reportedPost = new ReportedPost();
                reportedPost.setId(resultSet.getInt("id"));
                reportedPost.setUser_id(resultSet.getInt("user_id"));
                reportedPost.setPost_id(resultSet.getInt("post_id"));
                reportedPosts.add(reportedPost);
            }
        } catch (SQLException e) {
            throw  new DAOException("<HomeDAO> - GET ALL REPOSTED POSTS BY "+reportedUser_id+"- ERROR", e);
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return reportedPosts;
    }

    @Override
    public List<Post> getSearchedPost(String search) throws DAOException {
        List<Post> posts = new ArrayList<>();
        try {
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(GETSEARCHPOST);
            callableStatement.setString(1, search);
            resultSet = callableStatement.executeQuery();


            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setPost_title(resultSet.getString("post_title"));
                post.setPost_description(resultSet.getString("post_desc"));
                posts.add(post);
            }

        } catch (SQLException e) {
            throw  new DAOException("<HomeDAO> - GET SEARCHED POSTS "+search+" - ERROR", e);
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return posts;
    }

    @Override
    public List<Post> getPostsByGroupId(int group_id) throws DAOException {
        List<Post> posts = new ArrayList<>();
        try {
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(GETPOSTBYGROUPID);
            callableStatement.setInt(1,group_id);
            resultSet = callableStatement.executeQuery();


            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setPost_title(resultSet.getString("post_title"));
                post.setPost_description(resultSet.getString("post_desc"));
                posts.add(post);
            }
    } catch (SQLException e) {
            throw  new DAOException("<HomeDAO> - GET ALL POSTS BY GROUP ID "+group_id+"- ERROR", e);
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return posts ;
    }
}
