package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.entities.User;
import database.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for User entity.
 * @author Kush Rao
 */
public class UserDAO {
    /**
     * Creating table SQL script of User entity
     */
    private static final String SQL_CREATE_TABLE = "CREATE TABLE `member` (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, first_name VARCHAR(100) NOT NULL, last_name VARCHAR(100) NOT NULL, email VARCHAR(500) NOT NULL, password VARCHAR(200) NOT NULL, karma_points TINYINT NOT NULL, subscription_limit TINYINT NOT NULL, current_status TINYINT NOT NULL);";
    /**
     * Checking table existing SQL script of User entity
     */
    private static final String SQL_TABLE_EXISTS = "SELECT id, first_name, last_name, email, password, karma_points, subscription_limit, current_status FROM `member` LIMIT 1;";

    /**
     * Checking row existing SQL script of User entity
     */
    private static final String SQL_RECORD_EXISTS = "SELECT id from `member` WHERE id = ? LIMIT 1;";
    /**
     * Updating row SQL script of User entity
     */
    private static final String SQL_UPDATE_RECORD = "UPDATE `member` SET first_name = ?, last_name = ?, email = ?, password = ?, karma_points = ?, subscription_limit = ?, current_status = ? WHERE id = ?;";
    /**
     * Deleting row SQL script of User entity
     */
    private static final String SQL_DELETE_RECORD = "DELETE FROM `member` WHERE id = ?;";
    /**
     * Inserting row SQL script of User entity
     */
    private static final String SQL_INSERT_RECORD = "INSERT INTO `member` (first_name, last_name, email, password, karma_points, subscription_limit, current_status) VALUES (?, ?, ?, ?, ?, ?, ?);";
    /**
     * Finding row by id SQL script of User entity
     */
    private static final String SQL_FIND_BY_ID = "SELECT id, first_name, last_name, email, password, karma_points, subscription_limit, current_status FROM `member` WHERE id = ? LIMIT 1;";
    /**
     * Finding row by email SQL script of User entity
     */
    private static final String SQL_FIND_BY_EMAIL = "SELECT id, first_name, last_name, email, password, karma_points, subscription_limit, current_status FROM `member` WHERE email = ?;";

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
     * @param id an id
     * @return a DAO instance of User entity
     * @throws Exception is thrown when deleting user failed
     */
    public UserDAO delete(int id) throws Exception {
        createIfNotExists();
        Connection connection = DatabaseConfig.getInstance().loadDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_RECORD);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return this;
    }

    /**
     * Find user row by id
     * @param id an id
     * @return a NULL if not found, User instance if found
     * @throws Exception is thrown when finding user failed
     */
    public User findById(int id) throws Exception {
        createIfNotExists();
        DatabaseConfig.getInstance();
        Connection connection = DatabaseConfig.getInstance().loadDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
        preparedStatement.setInt(1, id);
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

        target.setId(resultSet.getInt(1));
        target.setFirstName(resultSet.getString(2));
        target.setLastName(resultSet.getString(3));
        target.setEmail(resultSet.getString(4));
        target.setPassword(resultSet.getString(5));
        target.setKarmaPoints(resultSet.getInt(6));
        target.setSubscriptionLimit(resultSet.getInt(7));
        target.setCurrentStatus(resultSet.getInt(8));

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
        DatabaseConfig.getInstance();
        Connection connection = DatabaseConfig.getInstance().loadDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_RECORD_EXISTS);
        preparedStatement.setInt(1, data.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            resultSet.close();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_RECORD);
            preparedStatement.setString(1, data.getFirstName());
            preparedStatement.setString(2, data.getLastName());
            preparedStatement.setString(3, data.getEmail());
            preparedStatement.setString(4, data.getPassword());
            preparedStatement.setInt(5, data.getKarmaPoints());
            preparedStatement.setInt(6, data.getSubscriptionLimit());
            preparedStatement.setInt(7, data.getCurrentStatus());
            preparedStatement.setInt(8, data.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } else {
            resultSet.close();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(SQL_INSERT_RECORD);
            preparedStatement.setString(1, data.getFirstName());
            preparedStatement.setString(2, data.getLastName());
            preparedStatement.setString(3, data.getEmail());
            preparedStatement.setString(4, data.getPassword());
            preparedStatement.setInt(5, data.getKarmaPoints());
            preparedStatement.setInt(6, data.getSubscriptionLimit());
            preparedStatement.setInt(7, data.getCurrentStatus());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    data.setId(generatedKeys.getInt(1));
                }
            }
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
            List<User> userList = findByEmail("support@geetopod.com");
            if (userList.size() == 0) {
                User user = new User();
                user.setPassword("geetopod");
                user.setEmail("support@geetopod.com");
                user.setFirstName("geeto");
                user.setLastName("Pod");
                save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
