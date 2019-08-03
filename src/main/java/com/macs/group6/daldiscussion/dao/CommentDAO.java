package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Comment;
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

/**
 * @author Smit Saraiya
 */
@Component("CommentDAO")
public class CommentDAO implements ICommentDAO {
    private static final Logger logger = Logger.getLogger(CommentDAO.class);
    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;

    private DatabaseConfig databaseConfig = DatabaseConfig.getInstance();
    private static final String GETCOMMENTSBYPOSTID = "{call getCommentsByPostId(?)}";
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
            throw  new DAOException("<CommentDAO> - "+postId+" - GET COMMENTS - ERROR", e);

        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
        return commentMap;
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
        }catch (SQLException e){
            throw  new DAOException("<CommentDAO> - "+post_id+" - ADD COMMENT - ERROR", e);
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
    }
}
