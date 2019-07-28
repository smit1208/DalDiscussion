package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.model.SubscriptionGroup;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("SubscriptionDAO")
public class SubscriptionDAO implements ISubscriptionDAO {
    private static final Logger logger = Logger.getLogger(SubscriptionDAO.class);
    private DatabaseConfig databaseConfig = DatabaseConfig.getInstance();
    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;

//    public SubscriptionDAO(@Qualifier("DatabaseConfig") DatabaseConfig databaseConfig){
//        this.databaseConfig = databaseConfig;
//    }
    private static final String GETSUBSCRIPTIONGROUPS = "{call getSubscriptionGroupList()}";
    private static final String ADDSUBSCRIPTIONREQUEST = "{call addSubscriptionRequest(?,?,?)}";
    private static final String FETCHSUBSCRIPTIONBYUSERID = "{call fetchSubscriptionByUserId(?)}";
    private static final String FETCHALLAPPROVEDREQUESTS = "{call fetchAllApprovedRequests(?)}";
    private static final String FETCHSUBSCRIPTIONBYID = "{call fetchSubscriptionByID(?)}";

    @Override
    public List<SubscriptionGroup> getAllSubscription() {
        List<SubscriptionGroup> subscriptionGroupList = new ArrayList<>();
        try {
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(GETSUBSCRIPTIONGROUPS);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                SubscriptionGroup subscriptionGroup = new SubscriptionGroup();

                subscriptionGroup.setId(resultSet.getInt("id"));
                subscriptionGroup.setName(resultSet.getString("name"));
                subscriptionGroup.setMax_count(resultSet.getInt("max_count"));

                subscriptionGroupList.add(subscriptionGroup);
            }
        }catch (Exception e){
            logger.error("Error in SubscriptionDAO while fetching subscriptions " +e.getMessage());
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
        return subscriptionGroupList;
    }

    @Override
    public void addSubscriptionRequest(int user_id, int group_id) {
        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(ADDSUBSCRIPTIONREQUEST);
            callableStatement.setInt(1,0);
            callableStatement.setInt(2,user_id);
            callableStatement.setInt(3,group_id);
            callableStatement.executeQuery();
        }catch (Exception e){
            logger.error("Error in SubscriptionDAO while adding subscriptions " +e.getMessage());
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
    }
    @Override
    public void addDefaultSubscriptionRequest(int user_id) {
        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(ADDSUBSCRIPTIONREQUEST);
            callableStatement.setInt(1,1);
            callableStatement.setInt(2,user_id);
            callableStatement.setInt(3,5);// Group 5 is general discussion , all users have access to it
            callableStatement.executeQuery();
        }catch (Exception e){
            logger.error("Error in SubscriptionDAO while adding default subscriptions " +e.getMessage());
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
    }
    @Override
    public List<Subscription> fetchSubscriptionByUserID(int user_id) {
        List<Subscription> subscriptions = new ArrayList<>();
        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(FETCHSUBSCRIPTIONBYUSERID);
            callableStatement.setInt(1,user_id);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Subscription subscription = new Subscription();
                subscription.setId(resultSet.getInt("id"));
                subscription.setStatus(resultSet.getBoolean("status"));
                subscription.setUser_id(resultSet.getInt("user_id"));
                subscription.setGroup_id(resultSet.getInt("group_id"));
                subscriptions.add(subscription);
            }
        }catch (Exception e){
            logger.error("Error in SubscriptionDAO while fetching subscriptions by user id " +e.getMessage());
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
        return subscriptions;
    }

    @Override
    public Map<String,Object> approvedSubscriptions(int user_id) {
        Map<String,Object> approvedSubscriptionMap = new HashMap<>();
        List<Subscription> subscriptions = new ArrayList<>();
        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(FETCHALLAPPROVEDREQUESTS);
            callableStatement.setInt(1,user_id);
            resultSet = callableStatement.executeQuery();

            while(resultSet.next()){

                Subscription subscription = new Subscription();
                subscription.setGroupName(resultSet.getString("name"));
                subscription.setGroup_id(resultSet.getInt("group_id"));
                subscriptions.add(subscription);
            }
            approvedSubscriptionMap.put("displayApprovedSubscriptions",subscriptions);
        }catch (Exception e){
            logger.error("Error in SubscriptionDAO while fetching approved subscriptions " +e.getMessage());
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
        return approvedSubscriptionMap;
    }

    @Override
    public Subscription fetchSubscriptionByID(int subscription_id) {
        Subscription subscription = new Subscription();
        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(FETCHSUBSCRIPTIONBYID);
            callableStatement.setInt(1,subscription_id);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()){

                subscription.setId(resultSet.getInt("id"));
                subscription.setStatus(resultSet.getBoolean("status"));
                subscription.setUser_id(resultSet.getInt("user_id"));
                subscription.setGroup_id(resultSet.getInt("group_id"));

            }
        }catch (Exception e){
            logger.error("Error in SubscriptionDAO while fetching subscriptions by id" +e.getMessage());
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
        return subscription;
    }
}
