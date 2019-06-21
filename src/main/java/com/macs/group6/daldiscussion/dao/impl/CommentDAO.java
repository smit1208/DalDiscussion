package com.macs.group6.daldiscussion.dao.impl;

import com.macs.group6.daldiscussion.dao.ICommentDAO;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import database.DatabaseConfig;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO implements ICommentDAO {
    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;

    private static ICommentDAO iCommentDAO;

    private static final String GETCOMMENTSBYPOSTID = "{call getCommentsByPostId(?)}";
    private static final String GETPOSTBYID = "{call getPostById(?)}";
    private static final String ADDCOMMENT = "{call addComment(?,?,?)}";

    public static ICommentDAO getInstance(){
        if(iCommentDAO == null){
            iCommentDAO = new CommentDAO();
        }
        return iCommentDAO;
    }

    @Override
    public List<Comment> getComments(int postId) {
        List<Comment> commentList = new ArrayList<>();
        try{
            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(GETCOMMENTSBYPOSTID);

            callableStatement.setInt(1,postId);
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setComment_description(resultSet.getString("comment_body"));
                commentList.add(comment);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
        return commentList;
    }




    @Override
    public Post getPostById(int postId) {
        Post post = new Post();
        try{
            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(GETPOSTBYID);
            callableStatement.setInt(1,postId);
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()){

                post.setId(resultSet.getInt("id"));
                post.setPost_title(resultSet.getString("post_title"));
                post.setPost_description(resultSet.getString("post_desc"));

            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
        return post;
    }

    @Override
    public void addComment(Comment comment, int post_id) {
        try{
            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(ADDCOMMENT);
            callableStatement.setString(1,comment.getComment_description());
            callableStatement.setInt(2,post_id);
            callableStatement.setInt(3,1);
            callableStatement.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
