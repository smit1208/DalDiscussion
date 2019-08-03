package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for User entity.
 *
 * @author Kush Rao
 */
@Component("UserDAO")
public class UserDAO {
    Connection connection = null;
    Statement statement = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    private static final Logger logger = Logger.getLogger(UserDAO.class);

    private DatabaseConfig databaseConfig;
    /**
     * Checking row existing SQL script of User entity
     */
    private static final String SQL_RECORD_EXISTS = "SELECT id from ` user` WHERE id = ? LIMIT 1;";
    /**
     * Updating row SQL script of User entity
     */
    private static final String SQL_UPDATE_RECORD = "UPDATE ` user` SET first_name = ?, last_name = ?, email = ?, password = ? WHERE id = ?;";
    /**
     * Deleting row SQL script of User entity
     */
    private static final String SQL_DELETE_RECORD = "DELETE FROM ` user` WHERE id = ?;";
    /**
     * Inserting row SQL script of User entity
     */
    private static final String SQL_INSERT_RECORD = "INSERT INTO ` user` (first_name, last_name, email, password, karma_points, subscription_limit, current_status) VALUES (?, ?, ?, ?, ?, ?, ?);";
    /**
     * Finding row by id SQL script of User entity
     */
    private static final String SQL_FIND_BY_ID = "SELECT id, first_name, last_name, email, password, karma_points, subscription_limit, current_status FROM ` user` WHERE id = ? LIMIT 1;";
    /**
     * Finding row by email SQL script of User entity
     */
    private static final String SQL_FIND_BY_EMAIL = "SELECT id, first_name, last_name, email, password, karma_points, subscription_limit, current_status FROM ` user` WHERE email = ?;";

    private static final String SQL_FETCH_USER_KARMA_POINTS = "SELECT karma_points from ` user` where id = ?";

    private static final String SQL_UPDATE_KARMA_POINTS = "UPDATE ` user` set karma_points = ? where id = ?";

    private static final String SQL_GET_USER_ID_BY_POST = "SELECT user_id from `post` where id =?";

    private static final String SQL_PROCEDURE_UPDATE_USER_BY_EMAIL = "{call updateUser(?, ?, ?, ?, ?)}";

    private static final String SQL_PROCEDURE_FIND_USER_GROUPS = "SELECT g.id, g.name FROM `groups` g WHERE g.id IN (" +
            "	" +
            "	SELECT group_id FROM subscription WHERE user_id = ?);";
    private static UserDAO __instance;

    /**
     * Singleton implementation of DAO class of User entity
     *
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
     *
     * @param id an id
     * @return a DAO instance of User entity
     * @throws Exception is thrown when deleting user failed
     */
    public UserDAO delete(int id) throws Exception {
        /* createIfNotExists();*/
        connection = DatabaseConfig.getInstance().loadDatabase();
        preparedStatement = connection.prepareStatement(SQL_DELETE_RECORD);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return this;
    }

    /**
     * Find user row by id
     *
     * @param id an id
     * @return a NULL if not found, User instance if found
     * @throws Exception is thrown when finding user failed
     */
    public User findById(int id) throws Exception {
        /* createIfNotExists();*/

        connection = DatabaseConfig.getInstance().loadDatabase();
        preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
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
     *
     * @param email a email
     * @return a list of User instance found
     * @throws Exception is thrown if finding user failed
     */
    public List<User> findByEmail(String email) throws Exception {
        /*createIfNotExists();*/
        List<User> target = new ArrayList<>();

        connection = DatabaseConfig.getInstance().loadDatabase();
        preparedStatement = connection.prepareStatement(SQL_FIND_BY_EMAIL);
        preparedStatement.setString(1, email);
        resultSet = preparedStatement.executeQuery();
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
     *
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
     *
     * @param data a User instance
     * @return a DAO instance of User entity
     * @throws Exception is thrown if upserting failed
     */
    public UserDAO save(User data) throws Exception {
        /* createIfNotExists();*/

        connection = DatabaseConfig.getInstance().loadDatabase();
        preparedStatement = connection.prepareStatement(SQL_RECORD_EXISTS);
        preparedStatement.setInt(1, data.getId());
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            resultSet.close();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_RECORD);
            preparedStatement.setString(1, data.getFirstName());
            preparedStatement.setString(2, data.getLastName());
            preparedStatement.setString(3, data.getEmail());
            preparedStatement.setString(4, data.getPassword());
            preparedStatement.setInt(5, data.getId());
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

    public int getUserIdByPostID(int postID) {
        int userId=0;
        try {
            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(SQL_GET_USER_ID_BY_POST);
            callableStatement.setInt(1, postID);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(userId);
                userId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Error in UserDAO while fetching user by post id " + e.getMessage());
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return userId;
    }

    public int getOriginalKarmaPoints(int userId) {
        int karmaPoints = 0;
        try {
            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(SQL_FETCH_USER_KARMA_POINTS);
            callableStatement.setInt(1, userId);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                karmaPoints = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            logger.error("Error in UserDAO while fetching user karma points" + e.getMessage());
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, resultSet);
        }
        return karmaPoints;
    }

    public void updateUserKarmaPoints(int karmaPoints, int postid) throws DAOException {
        int userId = getUserIdByPostID(postid);
        int originalKarmaPoints = getOriginalKarmaPoints(userId);
        int updatedKarmaPoints = originalKarmaPoints + karmaPoints;
        try {
            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(SQL_UPDATE_KARMA_POINTS);
            callableStatement.setInt(1, updatedKarmaPoints);
            callableStatement.setInt(2, userId);
            int result = callableStatement.executeUpdate();
            if (result > 0) {
                logger.info(" Karma points updated for user" + userId + " karma points " + updatedKarmaPoints);
            }
        } catch (SQLException e) {
            throw new DAOException("<UserDAO> - UPDATE KARMA POINTS FOR USER:"+userId+" - ERROR- ",e);
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection, callableStatement, null);
        }
    }

    public boolean updateUser(int id, String fname, String lname, String email, String password) {

        connection = DatabaseConfig.getInstance().loadDatabase();

        try {
            callableStatement = connection.prepareCall(SQL_PROCEDURE_UPDATE_USER_BY_EMAIL);
            callableStatement.setInt(1, id);
            callableStatement.setString(2, email);
            callableStatement.setString(3, fname);
            callableStatement.setString(4, lname);
            callableStatement.setString(5, password);
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.error("Error in UserDAO  in updating user profile " + e.getMessage());
            return false;
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
    }

    public List<String> getUserGroups(int id) {

        List<String> groups = new ArrayList<String>();
        connection = DatabaseConfig.getInstance().loadDatabase();

        try {
            preparedStatement = connection.prepareStatement(SQL_PROCEDURE_FIND_USER_GROUPS);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet result = preparedStatement.getResultSet();
            while (result.next()) {
                groups.add(result.getString(2));
            }
            return groups;
        } catch (SQLException e) {
            logger.error("Error in UserDAO in retriving group data" + e.getMessage());
            return new ArrayList<String>();
        } finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
    }
}
