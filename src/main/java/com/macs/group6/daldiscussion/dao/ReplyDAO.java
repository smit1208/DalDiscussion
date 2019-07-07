package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Reply;
import com.macs.group6.daldiscussion.database.DatabaseConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component("ReplyDAO")
public class ReplyDAO implements IReplyDAO {
    Connection connection = null;
    Statement statement = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;
    private DatabaseConfig databaseConfig;

    private final String ADDREPLY = "{call addReply(?,?,?)}";

    public ReplyDAO(@Qualifier("DatabaseConfig")DatabaseConfig databaseConfig){
        this.databaseConfig = databaseConfig;

    }

    @Override
    public List<Reply> getReplies(int commentId) {

            List<Reply> replyList = new ArrayList<>();

            try{
                connection = this.databaseConfig.loadDatabase();
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
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,statement,resultSet);
        }
    }
}
