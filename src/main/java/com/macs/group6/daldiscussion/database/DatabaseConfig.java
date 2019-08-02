package com.macs.group6.daldiscussion.database;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Smit Saraiya
 */
@Component("DatabaseConfig")
public class DatabaseConfig {
    private static DatabaseConfig databaseConfigInstance;
    private static final String URL = "spring.datasource.url";
    private static final String USER = "spring.datasource.username";
    private static final String PASSWORD = "spring.datasource.password";
    private static final String DRIVER = "spring.datasource.driver-class-name";

    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    public DatabaseConfig(){
        try(InputStream inputStream = new FileInputStream("src/main/resources/application.properties")){
            Properties properties = new Properties();
            properties.load(inputStream);
            url = properties.getProperty(URL);
            user = properties.getProperty(USER);
            password = properties.getProperty(PASSWORD);
            driver = properties.getProperty(DRIVER);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static DatabaseConfig getInstance(){
        if(databaseConfigInstance == null){
            databaseConfigInstance = new DatabaseConfig();
        }
        return databaseConfigInstance;
    }

    public Connection loadDatabase(){
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url,user,password);
            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }
    public void closeConnection(final Connection connection, Statement statement, ResultSet resultSet){
       if(connection != null){
           try{
               connection.close();
           }catch (Exception e){
               e.printStackTrace();
           }
       }
        if(statement != null){
            try{
                statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(resultSet != null){
            try{
                resultSet.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
