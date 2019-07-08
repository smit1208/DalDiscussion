package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.controller.HomepageController;
import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.entities.User;
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
 * @author Kush Rao
 */
@Component("UserDAO")
public class UserDAO {
    private static final Logger logger = Logger.getLogger(HomepageController.class);

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

    private static final String SQL_PROCEDURE_UPDATE_USER_BY_EMAIL=    "{call updateUser(?, ?, ?, ?, ?)}";
    
    private static final String SQL_PROCEDURE_FIND_USER_GROUPS=    "SELECT g.id, g.name FROM CSCI5308_6_DEVINT.`groups` g WHERE g.id IN (" + 
    		"	" + 
    		"	SELECT group_id FROM CSCI5308_6_DEVINT.subscription WHERE user_id = ?);";
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
       /* createIfNotExists();*/
        Connection connection =  DatabaseConfig.getInstance().loadDatabase();
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
       /* createIfNotExists();*/

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
        /*createIfNotExists();*/
        List<User> target = new ArrayList<>();

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
       /* createIfNotExists();*/

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
            preparedStatement.setInt(5,data.getId());
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

    public int getUserIdByPostID(int postID){
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        int userId = 0;
        try {
            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(SQL_GET_USER_ID_BY_POST);
            callableStatement.setInt(1,postID);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                userId = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,null);
        }
        return userId;
    }

   public int getOriginalKarmaPoints(int userId){
       Connection connection = null;
       CallableStatement callableStatement = null;
       ResultSet resultSet = null;
       int karmaPoints = 0;
       try {
           connection = DatabaseConfig.getInstance().loadDatabase();
           callableStatement = connection.prepareCall(SQL_FETCH_USER_KARMA_POINTS);
           callableStatement.setInt(1,userId);
           resultSet = callableStatement.executeQuery();
           while (resultSet.next()){
               karmaPoints = resultSet.getInt(1);
           }

       } catch (SQLException e) {
           e.printStackTrace();
       }
       finally {
           DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
       }
       return karmaPoints;
   }

    public void updateUserKarmaPoints(int karmaPoints, int postid){
        int userId = getUserIdByPostID(postid);
        int originalKarmaPoints = getOriginalKarmaPoints(userId);
        int updatedKarmaPoints = originalKarmaPoints + karmaPoints;
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = DatabaseConfig.getInstance().loadDatabase();
            callableStatement = connection.prepareCall(SQL_UPDATE_KARMA_POINTS);
            callableStatement.setInt(1,updatedKarmaPoints);
            callableStatement.setInt(2,userId);
            int result = callableStatement.executeUpdate();
            if(result > 0){
                logger.info(" Karma points updated for user" +userId+" karma points " +updatedKarmaPoints);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error in updating karmapoints");
        }
        finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,null);
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

    public boolean updateUser(int id,String fname,String lname,String email, String password) {

   	 Connection connection = DatabaseConfig.getInstance().loadDatabase();

   	 try {
   	 CallableStatement cStatement = connection.prepareCall(SQL_PROCEDURE_UPDATE_USER_BY_EMAIL);
   	 cStatement.setInt(1, id);
   	 cStatement.setString(2, email);
   	cStatement.setString(3, fname);
   	cStatement.setString(4, lname);
   	cStatement.setString(5, password);
   	cStatement.executeUpdate();

   	 System.out.println("profile updated");
   	 return true;

   	 }catch (Exception e) {
   		 e.printStackTrace();
   		 System.out.println("profile cannot be updated");
   		 return false;
		}

   }


   public List<String> getUserGroups(int id) {

   	List<String> groups=new ArrayList<String>();
  	 Connection connection = DatabaseConfig.getInstance().loadDatabase();

  	 try {

    PreparedStatement preparedStatement = connection.prepareStatement(SQL_PROCEDURE_FIND_USER_GROUPS);
    preparedStatement.setInt(1, id);

    preparedStatement.execute();
    ResultSet result =preparedStatement.getResultSet();

  	//System.out.println(cStatement.execute());
  	 //ResultSet result= cStatement.getResultSet();

  	 while(result.next()) {
  		 groups.add(result.getString(2));
  	 }

  	 return groups;

  	 }catch (Exception e) {
  		 e.printStackTrace();
  		 System.out.println("error retriving group data");
  		return new ArrayList<String>();
		}

  }
}
