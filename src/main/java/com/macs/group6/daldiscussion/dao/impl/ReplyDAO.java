package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Reply;
import database.DatabaseConfig;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReplyDAO implements IReplyDAO{
    Connection connection = null;
    Statement statement = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;

    private static IReplyDAO iReplyDAO;

    private final String ADDREPLY = "{call addReply(?,?,?)}";

    public static IReplyDAO getInstance(){
        if(iReplyDAO == null){
            iReplyDAO = new ReplyDAO();
        }
        return iReplyDAO;
    }

    @Override
    public List<Reply> getReplies(int commentId) {

            List<Reply> replyList = new ArrayList<>();

            try{
                connection = DatabaseConfig.getInstance().loadDatabase();
                statement = connection.createStatement();
                String sql = "select * from replies as r where r.comment_id="+commentId;
                resultSet = statement.executeQuery(sql);

                while (resultSet.next()){
                    Reply reply = new Reply();
                    reply.setId(resultSet.getInt("id"));
                    reply.setReply_description(resultSet.getString("description"));
                    replyList.add(reply);
                }

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                DatabaseConfig.getInstance().closeConnection(connection,statement,resultSet);
            }
            return replyList;
        }

    @Override
    public void addReply(Reply reply, int comment_id) {
        try{
            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(ADDREPLY);
            callableStatement.setString(1,reply.getReply_description());
            callableStatement.setInt(2,1);
            callableStatement.setInt(3,comment_id);
            callableStatement.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
