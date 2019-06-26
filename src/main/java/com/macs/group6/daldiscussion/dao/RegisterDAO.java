package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.UserRegister;
import com.macs.group6.daldiscussion.database.DatabaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class RegisterDAO implements IRegisterDAO {

    private static final Logger logger = LoggerFactory.getLogger(RegisterDAO.class);
    @Override
    public void create(UserRegister userRegister) {
        Connection connection = null;
        Statement statement = null;
        String password=userRegister.getPassword();
        System.out.println("Passowrd "+password);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println("Passowrd "+hashedPassword);

        if (BCrypt.checkpw(password,hashedPassword)){
            logger.info("Password hashed correctly");
        }
        else{
            logger.info("Password hashed incorrectly");
        }

        DatabaseConfig databaseConfig =  DatabaseConfig.getInstance();
        connection = databaseConfig.loadDatabase();
        try {
            statement = connection.createStatement();
            String query = "insert into ` user`(first_name, last_name, email, password,karma_points,subscription_limit,current_status) values('"+userRegister.getFname()+"','"+userRegister.getLname()+"','"+userRegister.getEmail()+"','"+hashedPassword+"',1,1,1);";
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