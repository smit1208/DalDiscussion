/*
@author Sharon Alva
*/
package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.exceptions.ErrorCode;
import com.macs.group6.daldiscussion.model.Post;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("PostDAO")
public class PostDAO implements IPostDAO {
    private static final Logger logger = Logger.getLogger(PostDAO.class);

    private static final String SQL_INSERT_POST = "insert into post(post_title, post_desc, user_id, category, group_id,is_image,report) values(?,?,?,?,?,?,?);";
    private static final String GETALLACTIVEPOST = "{call getAllActivePosts()}";
    private static final String UPDATEPOSTMODDATE = "{call updatePostModDate(?,?)}";
    private static final String UPDATEPOSTSTATUS = "{call updatePostStatus(?,?)}";


    Connection connection = null;
    CallableStatement callableStatement = null;
    private DatabaseConfig databaseConfig = DatabaseConfig.getInstance();
    int result ;
    private static IPostDAO iPostDAO;
    ResultSet resultSet = null;

    @Override
    public int createPost(Post post) throws DAOException{
    int id=0;
        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(SQL_INSERT_POST);
            callableStatement.setString(1,post.getPost_title());
            callableStatement.setString(2, post.getPost_description());
            callableStatement.setInt(3,post.getUser_id());
            callableStatement.setInt(4,post.getCategory());
            callableStatement.setInt(5,post.getGroup());
            callableStatement.setInt(6,post.getIsImage());
            callableStatement.setInt(7,0);
            result = callableStatement.executeUpdate();
            if(result==1){
                resultSet=callableStatement.getGeneratedKeys();

                if(resultSet.next()){
                    id=resultSet.getInt(1);
                }
            }

            logger.info("createPost post successful! rows updated "+result);

        }catch (SQLException e) {
           throw  new DAOException("<PostDAO> "+post.getUser_id()+"- CREATE POST - ERROR ", e, ErrorCode.INSERT_INTO_DB_ERROR);

        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
        return id;
    }


    @Override
    public List<Post> getAllActivePosts() throws DAOException {
        List<Post> postList = new ArrayList<>();
        try {
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(GETALLACTIVEPOST);
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setCreationDate(resultSet.getDate("creation_date"));
                post.setLastModificationDate(resultSet.getDate("modification_date"));
                postList.add(post);
            }

        } catch (SQLException e) {
            throw  new DAOException("GET ALL ACTIVE POST - ERROR", e, ErrorCode.RETRIVE_FROM_DB_ERROR);
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return postList;
    }

    @Override
    public void updatePostModificationDate(int post_id) throws DAOException {
        try{
            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(UPDATEPOSTMODDATE);
            callableStatement.setTimestamp(1,convert(new java.util.Date()));
            callableStatement.setInt(2, post_id);
            callableStatement.executeQuery();
            logger.info("update post successful! rows updated ");

        }catch (SQLException e) {
            throw  new DAOException(post_id+" - UPDATE POST MODIFICATION DATE - ERROR", e, ErrorCode.UPDATE_RECORD_DB_ERROR);
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,null);
        }
    }

    @Override
    public void updatePostStatus(Post post) throws DAOException {
        try{

            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(UPDATEPOSTSTATUS);
            callableStatement.setInt(1,post.getId());
            callableStatement.setInt(2, post.getIsAlive());
            callableStatement.executeQuery();
            logger.info("update post successful! rows updated ");

        }catch (Exception e) {
            throw  new DAOException(post.getId()+" - UPDATE POST STATUS - ERROR", e, ErrorCode.UPDATE_RECORD_DB_ERROR);
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
