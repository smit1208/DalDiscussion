package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Post;
import database.DatabaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class PostDAO implements IPostDAO {

    private static final String SQL_INSERT_POST = "insert into post(post_title, post_desc, user_id, category) values(?,?,?,?);";
    private static final String SQL_INSERT_POST_WITH_IMAGE = "insert into post(post_title, post_desc, user_id, category,image) values(?,?,?,?,?);";

    private static final Logger LOGGER = LogManager.getLogger(PostDAO.class);

    Connection connection = null;
    PreparedStatement insertStatement = null;
    CallableStatement callableStatement = null;
    int result ;
    private static IPostDAO iPostDAO;

    public static IPostDAO getInstance(){
        if(iPostDAO == null){
            iPostDAO = new PostDAO();
        }
        return iPostDAO;
    }

    @Override
    public void create(Post post) {

        try{
            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(SQL_INSERT_POST);
            callableStatement.setString(1,post.getPost_title());
            callableStatement.setString(2, post.getPost_description());
            callableStatement.setInt(3,1);
            callableStatement.setInt(4,post.getCategory());
            result = callableStatement.executeUpdate();
            LOGGER.info("create post successful! rows updated "+result);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,null);
        }
    }

    public void createPostWithImage(Post post, Blob postImageBlob) {

        try{
            connection = DatabaseConfig.getInstance().loadDatabase();
            insertStatement = connection.prepareStatement(SQL_INSERT_POST_WITH_IMAGE);
            insertStatement.setString(1,post.getPost_title());
            insertStatement.setString(2, post.getPost_description());
            insertStatement.setInt(3,1);
            insertStatement.setInt(4,post.getCategory());
            insertStatement.setBlob(5,postImageBlob);
            int result = insertStatement.executeUpdate();
            LOGGER.info("create post successful! rows updated "+result);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,null);
        }



    }
}