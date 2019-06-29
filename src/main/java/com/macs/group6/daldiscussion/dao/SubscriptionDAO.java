package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.model.SubscriptionGroup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component("SubscriptionDAO")
public class SubscriptionDAO implements ISubscriptionDAO {

    private DatabaseConfig databaseConfig;
    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;

    public SubscriptionDAO(@Qualifier("DatabaseConfig") DatabaseConfig databaseConfig){
        this.databaseConfig = databaseConfig;
    }
    private final String GETSUBSCRIPTIONGROUPS = "{call getSubscriptionGroupList()}";
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
            e.printStackTrace();
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
        return subscriptionGroupList;
    }
}
