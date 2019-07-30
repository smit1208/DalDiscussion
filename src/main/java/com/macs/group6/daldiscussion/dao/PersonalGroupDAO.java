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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("PersonalGroupDAO")
public class PersonalGroupDAO implements IPersonalGroupDAO {
    private static final Logger logger = Logger.getLogger(PersonalGroupDAO.class);
    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;

    private DatabaseConfig databaseConfig = DatabaseConfig.getInstance();
    private static final String GETPRIVATEPOSTSBYGROUPID = "{call getPrivatePostsByGroupID(?)}";

    @Override
    public Map<String, Object> getPrivatePostsByGroupID(int groupID) throws DAOException {
        Map<String,Object> privatePostsMap = new HashMap<>();
        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(GETPRIVATEPOSTSBYGROUPID);
            callableStatement.setInt(1,groupID);
            resultSet = callableStatement.executeQuery();

            List<Post> posts = new ArrayList<>();
            while (resultSet.next()){
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setPost_title(resultSet.getString("post_title"));
                post.setPost_description(resultSet.getString("post_desc"));
                posts.add(post);
            }
            privatePostsMap.put("privatePosts",posts);
        }catch (Exception e){
            throw  new DAOException("<PersonalGroupDAO> - GET POSTS BY GROUP ID "+groupID+" - ERROR", e, ErrorCode.RETRIVE_FROM_DB_ERROR);
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }

        return privatePostsMap;
    }
}
