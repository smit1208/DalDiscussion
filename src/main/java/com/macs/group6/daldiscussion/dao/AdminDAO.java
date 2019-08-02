package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Subscription;
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

@Component("AdminDAO")
public class AdminDAO implements IAdminDAO {
    private static final Logger logger = Logger.getLogger(AdminDAO.class);

    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;
    private static final String GETADMIN = "{call getAdmin()}";
    private static final String FETCHALLSUBSCRIPTIONREQUESTS = "{call fetchAllSubscriptionRequests()}";
    private static final String APPROVEREQUEST = "{call approveSubscriptionRequest(?)}";
    private static final String GETPOSTBYMAXREPORT = "{call getPostsByMaxReports()}";
    private DatabaseConfig databaseConfig = DatabaseConfig.getInstance();

    @Override
    public User getAdmin() throws DAOException {
        User user = new User();
        try {
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(GETADMIN);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setRole(resultSet.getInt("role"));
            }
        } catch (SQLException e) {
            throw  new DAOException("<AdminDAO> - GET ADMIN - ERROR", e);
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return user;
    }

    @Override
    public List<Subscription> fetchAllSubscriptionRequests() throws DAOException {
        List<Subscription> subscriptions = new ArrayList<>();

        try {
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(FETCHALLSUBSCRIPTIONREQUESTS);
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Subscription subscription = new Subscription();
                subscription.setId(resultSet.getInt("id"));
                subscription.setStatus(resultSet.getBoolean("status"));
                subscription.setUser_id(resultSet.getInt("user_id"));
                subscription.setGroup_id(resultSet.getInt("group_id"));
                subscriptions.add(subscription);
            }
        } catch (SQLException e) {
            throw  new DAOException("AdminDAO> - GET ALL SUBSCRIPTIONS - ERROR", e);
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return subscriptions;
    }

    @Override
    public void approveSubscription(int subscription_id) throws DAOException {
        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(APPROVEREQUEST);
            callableStatement.setInt(1,subscription_id);
            callableStatement.executeQuery();
        }catch (SQLException e){
            throw  new DAOException("AdminDAO> "+subscription_id+"- APPROVE SUBSCRIPTION - ERROR", e);
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
    }

    @Override
    public Map<String, Object> getPostsByMaxReports() throws DAOException {
        Map<String, Object> maxReportMap = new HashMap<>();
        List<User> users = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        try {
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(GETPOSTBYMAXREPORT);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                Post post = new Post();
                user.setFirstName(resultSet.getString("first_name"));
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getInt("USERID"));
                post.setPost_title(resultSet.getString("post_title"));
                post.setReport(resultSet.getInt("reportCount"));
                users.add(user);
                posts.add(post);
                maxReportMap.put("user",users);
                maxReportMap.put("post",posts);
            }
        } catch (SQLException e) {
            throw  new DAOException("AdminDAO> - GET POSTS BY REPORTS - ERROR", e);
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return maxReportMap;
    }
}

