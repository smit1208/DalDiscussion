/*
@author Sharon Alva
*/
package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.database.DatabaseConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.sql.*;

@Component("PostDAO")
public class PostDAO implements IPostDAO {
    private static final Logger logger = Logger.getLogger(PostDAO.class);


    /*Columns order post_title, post_desc, user_id, category, group_id, is_reported, is_image*/
    private static final String SQL_INSERT_POST = "insert into post(post_title, post_desc, user_id, category, group_id, is_image, report) values(?,?,?,?,?,?,?);";
    private static final String ADDPOST = "{call addPost(?,?,?,?,?,?,?)}";
    private static final String UPDATEPOSTMODDATE = "{call updatePostModDate(?,?)}";



    Connection connection = null;
    CallableStatement callableStatement = null;

    private DatabaseConfig databaseConfig;

    public PostDAO(@Qualifier("DatabaseConfig") DatabaseConfig databaseConfig){

        this.databaseConfig = databaseConfig;
    }

    @Override
    public int create(Post post,int user_id) {
    int id=0;
        try{
            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(SQL_INSERT_POST);
            callableStatement.setString(1,post.getPost_title());
            callableStatement.setString(2, post.getPost_description());
            callableStatement.setInt(3,user_id);
            callableStatement.setInt(4,post.getCategory());
            callableStatement.setInt(5,post.getGroup());
            callableStatement.setInt(6,post.getIsImage());
            callableStatement.setInt(7,0);
            callableStatement.executeUpdate();

            ResultSet rs = callableStatement.getGeneratedKeys();

            if(rs.next()){
                System.out.println("In rs");
                id=rs.getInt(1);
                System.out.println(id);
            }

            logger.info("create post successful! rows updated ");

        }catch (Exception e) {
            logger.error("Error in PostDAO while creating post " +e.getMessage());
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement, null);
        }
        return id;
    }


    @Override
    public void updatePostModificationDate(int post_id) {
        try{
            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(UPDATEPOSTMODDATE);
            callableStatement.setTimestamp(1,convert(new java.util.Date()));
            callableStatement.setInt(2, post_id);
            callableStatement.executeQuery();
            logger.info("update post successful! rows updated ");

        }catch (Exception e) {
            logger.error("Error in PostDAO while updating post modification date" +e.getMessage());
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,null);
        }
    }

    // Function to convert java.util Date to java.sql Timestamp in Java
    public static java.sql.Timestamp convert(java.util.Date date)
    {
        return new java.sql.Timestamp(date.getTime());
    }
}
