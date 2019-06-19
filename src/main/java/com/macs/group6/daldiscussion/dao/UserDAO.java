package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.entities.User;
import database.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * DAO class for User entity.
 * @author Kush Rao
 */
public class UserDAO {
    /**
     * Creating table SQL script of User entity
     */
    private static final String SQL_CREATE_TABLE = "CREATE TABLE users (code VARCHAR(36) PRIMARY KEY, kind INT NOT NULL, username VARCHAR(100) NOT NULL, password VARCHAR(200) NOT NULL, first_name VARCHAR(100) NOT NULL, last_name VARCHAR(100) NOT NULL, middle_name VARCHAR(100) NOT NULL, email VARCHAR(500) NOT NULL);";
    /**
     * Checking table existing SQL script of User entity
     */
    private static final String SQL_TABLE_EXISTS = "SELECT code, kind, username, password, first_name, last_name, middle_name, email FROM users LIMIT 1;";

    /**
     * Checking row existing SQL script of User entity
     */
    private static final String SQL_RECORD_EXISTS = "SELECT code from users WHERE code = ? LIMIT 1;";
    /**
     * Updating row SQL script of User entity
     */
    private static final String SQL_UPDATE_RECORD = "UPDATE users SET kind = ?, username = ?, password = ?, first_name = ?, last_name = ?, middle_name = ?, email = ? WHERE code = ?;";
    /**
     * Deleting row SQL script of User entity
     */
    private static final String SQL_DELETE_RECORD = "DELETE FROM users WHERE code = ?;";
    /**
     * Inserting row SQL script of User entity
     */
    private static final String SQL_INSERT_RECORD = "INSERT INTO users (code, kind, username, password, first_name, last_name, middle_name, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    /**
     * Finding row by code SQL script of User entity
     */
    private static final String SQL_FIND_BY_CODE = "SELECT code, kind, username, password, first_name, last_name, middle_name, email FROM users WHERE code = ? LIMIT 1;";
    /**
     * Finding row by email SQL script of User entity
     */
    private static final String SQL_FIND_BY_EMAIL = "SELECT code, kind, username, password, first_name, last_name, middle_name, email FROM users WHERE email = ?;";
    /**
     * Finding row by username SQL script of User entity
     */
    private static final String SQL_FIND_BY_USERNAME = "SELECT code, kind, username, password, first_name, last_name, middle_name, email FROM users WHERE username = ?;";

    private static UserDAO __instance;

    /**
     * Singleton implementation of DAO class of User entity
     * @return a DAO instance of User entity
     */
    public static UserDAO getInstance() {
        if (__instance == null) {
            __instance = new UserDAO();
        }
        return __instance;
    }

    /**
     * Delete user row by usercode
     * @param code a usercode
     * @return a DAO instance of User entity
     * @throws Exception is thrown when deleting user failed
     */
    public UserDAO delete(String code) throws Exception {
        createIfNotExists();
        Connection connection = DatabaseConfig.getInstance().loadDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_RECORD);
        preparedStatement.setString(1, code);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return this;
    }

    /**
     * Find user row by usercode
     * @param code a usercode
     * @return a NULL if not found, User instance if found
     * @throws Exception is thrown when finding user failed
     */
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

    /**
     * Find user row by username
     * @param username a username
     * @return a list user instance found
     * @throws Exception is thrown if finding user failed
     */
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

    /**
     * Find user row by email
     * @param email a email
     * @return a list of User instance found
     * @throws Exception is thrown if finding user failed
     */
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

    /**
     * Parse result set to User instance
     * @param resultSet a result set
     * @return a User instance
     * @throws Exception is thrown if parsing failed
     */
    public User parse(ResultSet resultSet) throws Exception {
        User target = new User();
        target.setCode(resultSet.getString(1));
        target.setKind(resultSet.getInt(2));
        target.setUsername(resultSet.getString(3));
        target.setPassword(resultSet.getString(4));
        target.setFirstName(resultSet.getString(5));
        target.setLastName(resultSet.getString(6));
        target.setMiddleName(resultSet.getString(7));
        target.setEmail(resultSet.getString(8));
        return target;
    }

    /**
     * Insert or update User instance
     * @param data a User instance
     * @return a DAO instance of User entity
     * @throws Exception is thrown if upserting failed
     */
    public UserDAO save(User data) throws Exception {
        createIfNotExists();
        if (data.getCode() == null || data.getCode().trim().length() == 0) {
            data.setCode(UUID.randomUUID().toString().replaceAll("-", ""));
        }
        DatabaseConfig.getInstance();
        Connection connection = DatabaseConfig.getInstance().loadDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_RECORD_EXISTS);
        preparedStatement.setString(1, data.getCode());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            resultSet.close();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_RECORD);
            preparedStatement.setInt(1, data.getKind());
            preparedStatement.setString(2, data.getUsername());
            preparedStatement.setString(3, data.getPassword());
            preparedStatement.setString(4, data.getFirstName());
            preparedStatement.setString(5, data.getLastName());
            preparedStatement.setString(6, data.getMiddleName());
            preparedStatement.setString(7, data.getEmail());
            preparedStatement.setString(8, data.getCode());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } else {
            resultSet.close();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(SQL_INSERT_RECORD);
            preparedStatement.setString(1, data.getCode());
            preparedStatement.setInt(2, data.getKind());
            preparedStatement.setString(3, data.getUsername());
            preparedStatement.setString(4, data.getPassword());
            preparedStatement.setString(5, data.getFirstName());
            preparedStatement.setString(6, data.getLastName());
            preparedStatement.setString(7, data.getMiddleName());
            preparedStatement.setString(8, data.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        return this;
    }

    /**
     * Create User table if there is not
     * @return a DAO instance of User entity
     */
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
            createTestUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Check if User table exists or not
     * @return a true if User table exists, a false if not
     */
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

    /**
     * Create tested user if there is not
     */
    public void createTestUsers() {
        try {
            User user = findByCode("geetopod");
            if (user == null) {
                user = new User();
                user.setCode("geetopod");
                user.setUsername("geetopod");
                user.setPassword("geetopod");
                user.setEmail("support@geetopod.com");
                user.setFirstName("geeto");
                user.setLastName("Pod");
                user.setMiddleName("");
                save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
