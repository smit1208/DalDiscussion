package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.AppConfig;
import com.macs.group6.daldiscussion.model.UserRegister;

import com.macs.group6.daldiscussion.database.DatabaseConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

public class RegisterDaoImpl implements IRegisterDao {
    private static final Logger LOGGER = LogManager.getLogger(RegisterDaoImpl.class);

    private static final String SQL_INSERT_RECORD = "INSERT INTO ` user` (first_name, last_name, email, password, karma_points, subscription_limit, current_status) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private int  d_karma_points = AppConfig.getInstance().get_defaultKarmaPoints();
    private int  d_subscription_limit = AppConfig.getInstance().get_defaultSubscriptionLimit();
    private int  d_current_status = AppConfig.getInstance().get_defaultCurrentStatus();

    Connection connection = null;
    PreparedStatement insertStatement = null;
    CallableStatement callableStatement = null;
    int result = 0;
    @Override
    public void create(UserRegister userRegister ) {


        connection = DatabaseConfig.getInstance().loadDatabase();
        try {
            callableStatement = connection.prepareCall(SQL_INSERT_RECORD);
            callableStatement.setString(1,userRegister.getFname());
            callableStatement.setString(2, userRegister.getLname());
            callableStatement.setString(3, userRegister.getEmail());
            callableStatement.setString(4, userRegister.getPassword());
            callableStatement.setInt(5,d_karma_points);
            callableStatement.setInt(6,d_subscription_limit);
            callableStatement.setInt(7,d_current_status);
            result = callableStatement.executeUpdate();
            } catch (SQLException e) {

            e.printStackTrace();
        }
        finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,null);
        }
    }


}