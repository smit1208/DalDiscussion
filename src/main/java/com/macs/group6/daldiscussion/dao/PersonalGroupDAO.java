package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;

    private DatabaseConfig databaseConfig;
    private static final String GETPRIVATEPOSTSBYGROUPID = "{call getPrivatePostsByGroupID(?)}";
    @Autowired
    public PersonalGroupDAO(@Qualifier("DatabaseConfig") DatabaseConfig databaseConfig){
        this.databaseConfig = databaseConfig;
    }

    @Override
    public Map<String, Object> getPrivatePostsByGroupID(int groupID) {
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
            e.printStackTrace();
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }

        return privatePostsMap;
    }
}
