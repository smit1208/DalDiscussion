package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.database.DatabaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component("PostDAO")
public class PostDAO implements IPostDAO {

    private static final String SQL_INSERT_POST = "insert into post(post_title, post_desc, user_id, category) values(?,?,?,?);";
    private static final String SQL_INSERT_POST_WITH_IMAGE = "insert into post(post_title, post_desc, user_id, category,image) values(?,?,?,?,?);";

    private static final Logger LOGGER = LogManager.getLogger(PostDAO.class);

    Connection connection = null;
    CallableStatement callableStatement = null;
    private DatabaseConfig databaseConfig;
    int result ;
    private static IPostDAO iPostDAO;

    public PostDAO(@Qualifier("DatabaseConfig") DatabaseConfig databaseConfig){
        this.databaseConfig = databaseConfig;
    }

    @Override
    public void create(Post post,int user_id) {

        try{
            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(SQL_INSERT_POST);
            callableStatement.setString(1,post.getPost_title());
            callableStatement.setString(2, post.getPost_description());
            callableStatement.setInt(3,user_id);
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
            callableStatement = connection.prepareCall(SQL_INSERT_POST_WITH_IMAGE);
            callableStatement.setString(1,post.getPost_title());
            callableStatement.setString(2, post.getPost_description());
            callableStatement.setInt(3,1);
            callableStatement.setInt(4,post.getCategory());
            callableStatement.setBlob(5,postImageBlob);
            int result = callableStatement.executeUpdate();
            LOGGER.info("create post successful! rows updated "+result);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,null);
        }



    }
}
