package com.macs.group6.daldiscussion.dao.impl;

import com.macs.group6.daldiscussion.dao.IPostDao;
import com.macs.group6.daldiscussion.model.Post;
import database.DatabaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class PostDao implements IPostDao {
    private static final Logger LOGGER = LogManager.getLogger(PostDao.class);
    @Override
    public void create(Post post) {
        Connection connection = null;
        PreparedStatement insertStatement = null;

        try{
            DatabaseConfig databaseConfig = new DatabaseConfig();
            connection = databaseConfig.loadDatabase();
            String query = "insert into post(post_title, post_desc, user_id, category) values(?,?,?,?);";
            insertStatement = connection.prepareStatement(query);
            insertStatement.setString(1,post.getPost_title());
            insertStatement.setString(2, post.getPost_description());
            insertStatement.setInt(3,1);
            insertStatement.setInt(4,post.getCategory());
            int result = insertStatement.executeUpdate();
            LOGGER.info("create successful! rows updated "+result);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(insertStatement!=null){
                    insertStatement.close();
                }
                if(connection!=null){
                    connection.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }

    public void createPostWithImage(Post post, Blob postImageBlob) {

        Connection connection = null;
        PreparedStatement insertStatement = null;


        try{
            DatabaseConfig databaseConfig = new DatabaseConfig();
            connection = databaseConfig.loadDatabase();
            String query = "insert into post(post_title, post_desc, user_id, category,image) values(?,?,?,?,?);";
            insertStatement = connection.prepareStatement(query);
            insertStatement.setString(1,post.getPost_title());
            insertStatement.setString(2, post.getPost_description());
            insertStatement.setInt(3,1);
            insertStatement.setInt(4,post.getCategory());
            insertStatement.setBlob(5,postImageBlob);
            int result = insertStatement.executeUpdate();
            LOGGER.info("create successful! rows updated "+result);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(insertStatement!=null){
                    insertStatement.close();
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
