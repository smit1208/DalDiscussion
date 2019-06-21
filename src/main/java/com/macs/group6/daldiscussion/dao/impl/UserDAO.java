package com.macs.group6.daldiscussion.dao.impl;

import com.macs.group6.daldiscussion.entities.User;
import database.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO {
    private static final String SQL_CREATE_TABLE = "CREATE TABLE users (code VARCHAR(36) PRIMARY KEY, kind INT NOT NULL, username VARCHAR(100) NOT NULL, password VARCHAR(200) NOT NULL, first_name VARCHAR(100) NOT NULL, last_name VARCHAR(100) NOT NULL, middle_name VARCHAR(100) NOT NULL, email VARCHAR(500) NOT NULL);";
    private static final String SQL_TABLE_EXISTS = "SELECT code, kind, username, password, first_name, last_name, middle_name, email FROM users LIMIT 1;";

    private static final String SQL_RECORD_EXISTS = "SELECT code from users WHERE code = ? LIMIT 1;";
    private static final String SQL_UPDATE_RECORD = "UPDATE users SET kind = ?, username = ?, password = ?, first_name = ?, last_name = ?, middle_name = ?, email = ? WHERE code = ?;";
    private static final String SQL_DELETE_RECORD = "DELETE FROM users WHERE code = ?;";
    private static final String SQL_INSERT_RECORD = "INSERT INTO users (code, kind, username, password, first_name, last_name, middle_name, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_FIND_BY_CODE = "SELECT code, kind, username, password, first_name, last_name, middle_name, email FROM users WHERE code = ? LIMIT 1;";
    private static final String SQL_FIND_BY_EMAIL = "SELECT code, kind, username, password, first_name, last_name, middle_name, email FROM users WHERE email = ?;";
    private static final String SQL_FIND_BY_USERNAME = "SELECT code, kind, username, password, first_name, last_name, middle_name, email FROM users WHERE username = ?;";

    private static UserDAO __instance;

    public static UserDAO instance() {
        if (__instance == null) {
            __instance = new UserDAO();
        }
        return __instance;
    }

    public UserDAO delete(String code) throws Exception {
        Connection connection = DatabaseConfig.getInstance().loadDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_RECORD);
        preparedStatement.setString(1, code);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return this;
    }

    public User findByCode(String code) throws Exception {
        createIfNotExists();
        DatabaseConfig.getInstance();
        Connection connection = DatabaseConfig.getInstance().loadDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_CODE);
        preparedStatement.setString(1, code);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User target = parse(resultSet);
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return target;
        } else {
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return null;
        }
    }

    public List<User> findByUsername(String username) throws Exception {
        createIfNotExists();
        List<User> target = new ArrayList<>();
        DatabaseConfig.getInstance();
        Connection connection = DatabaseConfig.getInstance().loadDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_USERNAME);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = parse(resultSet);
            target.add(user);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return target;
    }

    public List<User> findByEmail(String email) throws Exception {
        createIfNotExists();
        List<User> target = new ArrayList<>();
        DatabaseConfig.getInstance();
        Connection connection = DatabaseConfig.getInstance().loadDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_EMAIL);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = parse(resultSet);
            target.add(user);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return target;
    }

    public User parse(ResultSet resultSet) throws Exception {
        User target = new User();
        target.code = resultSet.getString(1);
        target.kind = resultSet.getInt(2);
        target.username = resultSet.getString(3);
        target.password = resultSet.getString(4);
        target.firstName = resultSet.getString(5);
        target.lastName = resultSet.getString(6);
        target.middleName = resultSet.getString(7);
        target.email = resultSet.getString(8);
        return target;
    }

    public UserDAO save(User data) throws Exception {
        createIfNotExists();
        if (data.code == null || data.code.trim().length() == 0) {
            data.code = UUID.randomUUID().toString().replaceAll("-", "");
        }
        DatabaseConfig.getInstance();
        Connection connection = DatabaseConfig.getInstance().loadDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_RECORD_EXISTS);
        preparedStatement.setString(1, data.code);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            resultSet.close();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_RECORD);
            preparedStatement.setInt(1, data.kind);
            preparedStatement.setString(2, data.username);
            preparedStatement.setString(3, data.password);
            preparedStatement.setString(4, data.firstName);
            preparedStatement.setString(5, data.lastName);
            preparedStatement.setString(6, data.middleName);
            preparedStatement.setString(7, data.email);
            preparedStatement.setString(8, data.code);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } else {
            resultSet.close();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(SQL_INSERT_RECORD);
            preparedStatement.setString(1, data.code);
            preparedStatement.setInt(2, data.kind);
            preparedStatement.setString(3, data.username);
            preparedStatement.setString(4, data.password);
            preparedStatement.setString(5, data.firstName);
            preparedStatement.setString(6, data.lastName);
            preparedStatement.setString(7, data.middleName);
            preparedStatement.setString(8, data.email);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        return this;
    }

    public UserDAO createIfNotExists() {
        try {
            if (!isTableExists()) {
                DatabaseConfig.getInstance();
                Connection connection = DatabaseConfig.getInstance().loadDatabase();
                Statement statement = connection.createStatement();
                statement.execute(SQL_CREATE_TABLE);
                statement.close();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isTableExists() {
        try {
            DatabaseConfig.getInstance();
            Connection connection = DatabaseConfig.getInstance().loadDatabase();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_TABLE_EXISTS);

            if (resultSet.next()) {
            }

            resultSet.close();
            statement.close();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createTestUsers() {
        try {
            User user = findByCode("geetopod");
            if (user == null) {
                user = new User();
                user.code = "geetopod";
                user.username = "geetopod";
                user.password = "geetopod";
                user.email = "support@geetopod.com";
                user.firstName = "geeto";
                user.lastName = "Pod";
                user.middleName = "";
                save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
