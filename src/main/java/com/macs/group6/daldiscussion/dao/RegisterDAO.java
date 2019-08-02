package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.AppConfig;

import com.macs.group6.daldiscussion.database.DatabaseConfig;

import com.macs.group6.daldiscussion.entities.User;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component("RegisterDAO")
public class RegisterDAO implements IRegisterDAO {
    private static final Logger logger = Logger.getLogger(RegisterDAO.class);

    private static final String SQL_INSERT_RECORD = "INSERT INTO ` user` (first_name, last_name, email, password, karma_points, subscription_limit, current_status) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_SELECT_USER_BY_EMAIL = "SELECT id from  ` user` where email = ?";
    private int  d_karma_points = AppConfig.getInstance().getDefaultKarmaPoints();
    private int  d_subscription_limit = AppConfig.getInstance().getDefaultSubscriptionLimit();
    private int  d_current_status = AppConfig.getInstance().getDefaultCurrentStatus();

    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;
    int result = 0;

    @Override
    public int create(User userRegister ) throws DAOException {

        connection = DatabaseConfig.getInstance().loadDatabase();
        try {
            callableStatement = connection.prepareCall(SQL_INSERT_RECORD);
            callableStatement.setString(1,userRegister.getFirstName());
            callableStatement.setString(2, userRegister.getLastName());
            callableStatement.setString(3, userRegister.getEmail());
            callableStatement.setString(4, userRegister.getPassword());
            callableStatement.setInt(5,d_karma_points);
            callableStatement.setInt(6,d_subscription_limit);
            callableStatement.setInt(7,d_current_status);
            result = callableStatement.executeUpdate();

            } catch (SQLException e) {
            throw  new DAOException("<RegisterDAO> - CREATE USER - ERROR ", e);
        }
        finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,null);
        }
        return result;
    }

    public boolean userExists(User userRegister) throws DAOException {
        boolean isPresent = false;
        connection = DatabaseConfig.getInstance().loadDatabase();
        try {
            callableStatement = connection.prepareCall(SQL_SELECT_USER_BY_EMAIL);
            callableStatement.setString(1,userRegister.getEmail());
            resultSet = callableStatement.executeQuery();

            if(resultSet.next()){
                isPresent = true;
            }
         }
        catch (SQLException e){
            throw  new DAOException("<RegisterDAO> - USER EXITS "+userRegister.getEmail()+" - ERROR ", e);
        }

        finally {
        DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
        return isPresent;
    }
}