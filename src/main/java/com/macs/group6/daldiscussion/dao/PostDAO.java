/*
@author Sharon Alva
*/
package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Post;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("PostDAO")
public class PostDAO implements IPostDAO {
    private static final Logger logger = Logger.getLogger(PostDAO.class);
    private static final String GETPOSTBYID = "{call getPostById(?)}";
    private static final String GETALLACTIVEPOST = "{call getAllActivePosts()}";
    private static final String UPDATEPOSTSTATUS = "{call updatePostStatus(?,?)}";
    private static final String ADDPOST = "{call addPost(?,?,?,?,?,?,?,?)}";

    Connection connection = null;
    CallableStatement callableStatement = null;
    private DatabaseConfig databaseConfig = DatabaseConfig.getInstance();
    int result ;
    ResultSet resultSet = null;

    @Override
    public int createPost(Post post) throws DAOException{
    int id=0;
        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(ADDPOST);
            callableStatement.setString(1,post.getPost_title());
            callableStatement.setString(2, post.getPost_description());
            callableStatement.setInt(3,post.getUser_id());
            callableStatement.setInt(4,post.getCategory());
            callableStatement.setInt(5,post.getGroup());
            callableStatement.setInt(6,post.getIsImage());
            callableStatement.setInt(7,0);
            callableStatement.registerOutParameter(8, Types.INTEGER);
            result = callableStatement.executeUpdate();
            if(result==1){
                id = callableStatement.getInt(8);
            }
            logger.info("createPost post successful! rows updated "+result);

        }catch (SQLException e) {
           throw  new DAOException("<PostDAO> USER_ID"+post.getUser_id()+"- CREATE POST - ERROR ", e);

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
            throw  new DAOException("<PostDAO> - GET ALL ACTIVE POST - ERROR", e);
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return postList;
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

        }catch (SQLException e) {
            throw  new DAOException("<PostAO> POST_ID:"+post.getId()+" - UPDATE POST STATUS - ERROR", e);
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,null);
        }
    }
    @Override
    public Post getPostById(int postId) throws DAOException {
        Post post = new Post();
        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(GETPOSTBYID);
            callableStatement.setInt(1,postId);
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()){

                post.setId(resultSet.getInt("id"));
                post.setPost_title(resultSet.getString("post_title"));
                post.setPost_description(resultSet.getString("post_desc"));

            }

        }catch (SQLException e) {
            throw  new DAOException("<PostDAO> - POST_ID: "+postId+" - GET POST - ERROR", e);

        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
        return post;
    }

}
