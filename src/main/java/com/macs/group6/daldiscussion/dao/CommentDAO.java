package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.exceptions.ErrorCode;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("CommentDAO")
public class CommentDAO implements ICommentDAO {
    private static final Logger logger = Logger.getLogger(CommentDAO.class);
    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;

    private DatabaseConfig databaseConfig = DatabaseConfig.getInstance();
    private static final String GETCOMMENTSBYPOSTID = "{call getCommentsByPostId(?)}";
    private static final String GETPOSTBYID = "{call getPostById(?)}";
    private static final String ADDCOMMENT = "{call addComment(?,?,?,?)}";
    private static final String COMMENTBYNAME = "{call getCommentByName()}";


    @Override
    public Map<String, Object> getComments(int postId) throws DAOException {
        Map<String,Object> commentMap = new HashMap<>();

        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(GETCOMMENTSBYPOSTID);
            List<Comment> commentList = new ArrayList<>();
            callableStatement.setInt(1,postId);
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setComment_description(resultSet.getString("comment_body"));
                comment.setCommentBy(resultSet.getString("commentBy"));
                commentList.add(comment);

            }
                commentMap.put("commentList",commentList);


        }catch (SQLException e){
            commentMap.put("Error","Error in fetching the comments");
            throw  new DAOException("<CommentDAO> - "+postId+" - GET COMMENTS - ERROR", e, ErrorCode.RETRIVE_FROM_DB_ERROR);

        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
        return commentMap;
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
            throw  new DAOException("<CommentDAO> - "+postId+" - GET POST - ERROR", e, ErrorCode.RETRIVE_FROM_DB_ERROR);

        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
        return post;
    }

    @Override
    public void addComment(Comment comment, int post_id, int user_id, String name) throws DAOException {
        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(ADDCOMMENT);
            callableStatement.setString(1,comment.getComment_description());
            callableStatement.setInt(2,post_id);
            callableStatement.setInt(3,user_id);
            callableStatement.setString(4,name);
            callableStatement.executeQuery();
        }catch (Exception e){
            throw  new DAOException("<CommentDAO> - "+post_id+" - ADD COMMENT - ERROR", e, ErrorCode.INSERT_INTO_DB_ERROR);
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
    }
}
