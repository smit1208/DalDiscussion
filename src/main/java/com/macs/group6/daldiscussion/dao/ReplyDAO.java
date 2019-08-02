package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Reply;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("ReplyDAO")
public class ReplyDAO implements IReplyDAO {
    private static final Logger logger = Logger.getLogger(ReplyDAO.class);

    Connection connection = null;
    Statement statement = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;
    private DatabaseConfig databaseConfig = DatabaseConfig.getInstance();

    private final String ADDREPLY = "{call addReply(?,?,?,?)}";


    @Override
    public List<Reply> getReplies(int commentId) throws DAOException {

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
                    reply.setReplyBy(resultSet.getString("replyBy"));
                    replyList.add(reply);
                }

            }catch (SQLException e){
                throw  new DAOException("<ReplyDAO> - GET ALL REPLIES FOR COMMENT"+commentId+" - ERROR ", e);
            }finally {
                DatabaseConfig.getInstance().closeConnection(connection,statement,resultSet);
            }
            return replyList;
        }

    @Override
    public void addReply(Reply reply, int comment_id,int user_id, String name) throws DAOException {
        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(ADDREPLY);
            callableStatement.setString(1,reply.getReply_description());
            callableStatement.setInt(2,user_id);
            callableStatement.setInt(3,comment_id);
            callableStatement.setString(4,name);
            callableStatement.executeQuery();
    }catch (SQLException e){
            throw  new DAOException("<ReplyDAO> - ADD REPLY FOR COMMENT"+comment_id+" - ERROR ", e);
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,statement,resultSet);
        }
    }
}
