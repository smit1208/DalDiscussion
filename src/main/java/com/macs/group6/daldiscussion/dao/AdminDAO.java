package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.controller.AdminController;
import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.model.Subscription;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component("AdminDAO")
public class AdminDAO implements IAdminDAO {
    private static final Logger logger = Logger.getLogger(AdminDAO.class);

    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;
    private static final String GETADMIN = "{call getAdmin()}";
    private static final String FETCHALLSUBSCRIPTIONREQUESTS = "{call fetchAllSubscriptionRequests()}";
    private static final String APPROVEREQUEST = "{call approveSubscriptionRequest(?)}";
    private DatabaseConfig databaseConfig;

    public AdminDAO(@Qualifier("DatabaseConfig") DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public User getAdmin() {
        User user = new User();
        try {
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(GETADMIN);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setRole(resultSet.getInt("role"));
            }
        } catch (Exception e) {
            logger.error("Error in fetching admin info " +e.getMessage());
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return user;
    }

    @Override
    public List<Subscription> fetchAllSubscriptionRequests() {
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
        } catch (Exception e) {
            logger.error("Error in fetching all subscription requests " +e.getMessage());
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return subscriptions;
    }

    @Override
    public void approveSubscription(int subscription_id) {
        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(APPROVEREQUEST);
            callableStatement.setInt(1,subscription_id);
            callableStatement.executeQuery();
        }catch (Exception e){
            logger.error("Error in approving subscription requests " +e.getMessage());
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
    }
}

