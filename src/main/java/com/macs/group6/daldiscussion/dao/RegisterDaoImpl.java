package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.UserRegister;
import com.macs.group6.daldiscussion.service.UserService;
import database.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterDaoImpl implements RegisterDao {
    @Override
    public void create(UserRegister userRegister ) {
        Connection connection = null;
        Statement statement = null;

        DatabaseConfig databaseConfig = new DatabaseConfig();
        connection = databaseConfig.loadDatabase();
        try {
            statement = connection.createStatement();
            String query = "insert into ` user`(first_name, last_name, email, password,karma_points,subscription_limit,current_status) values('"+userRegister.getFname()+"','"+userRegister.getLname()+"','"+userRegister.getEmail()+"','"+userRegister.getPassword()+"',1,1,1);";
            System.out.println(query);
            int result = statement.executeUpdate(query);
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

                if(statement!=null){
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if(connection!=null){
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}