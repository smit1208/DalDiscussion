package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

@Component("AdminDAO")
public class AdminDAO implements IAdminDAO {
    Connection connection = null;
    CallableStatement statement = null;
    ResultSet resultSet = null;
    private static final String GETADMIN = "{call getAdmin()}";
    private DatabaseConfig databaseConfig;
    public AdminDAO(@Qualifier("DatabaseConfig") DatabaseConfig databaseConfig){
        this.databaseConfig = databaseConfig;
    }

    @Override
    public User getAdmin() {
        User user = new User();
        try {
            connection = this.databaseConfig.loadDatabase();
            statement = connection.prepareCall(GETADMIN);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setRole(resultSet.getInt("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, statement, resultSet);
        }
        return user;
    }
}

