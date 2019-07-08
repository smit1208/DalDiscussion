package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.entities.Verification;
import com.macs.group6.daldiscussion.database.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

/**
 * DAO class for Verification entity.
 * @author Kush Rao
 */
public class VerificationDAO {
    /**
     * Creating table SQL script of Verification entity
     */
    private static final String SQL_CREATE_TABLE = "CREATE TABLE verification (code VARCHAR(36) PRIMARY KEY, kind INT NOT NULL, token_code VARCHAR(200) NOT NULL, token_text VARCHAR(200) NOT NULL, json_data VARCHAR(8000) NOT NULL, verify_token VARCHAR(36) NOT NULL, verified_token VARCHAR(36) NOT NULL);";
    /**
     * Checking table existing SQL script of Verification entity
     */
    private static final String SQL_TABLE_EXISTS = "SELECT code, kind, token_code, token_text, json_data, verify_token, verified_token FROM verification LIMIT 1;";

    /**
     * Checking row existing SQL script of Verification entity
     */
    private static final String SQL_RECORD_EXISTS = "SELECT code from verification WHERE code = ? LIMIT 1;";
    /**
     * Updating row SQL script of Verification entity
     */
    private static final String SQL_UPDATE_RECORD = "UPDATE verification SET kind = ?, token_code = ?, token_text = ?, json_data = ?, verify_token = ?, verified_token = ? WHERE code = ?;";
    /**
     * Inserting row SQL script of Verification entity
     */
    private static final String SQL_INSERT_RECORD = "INSERT INTO verification (code, kind, token_code, token_text, json_data, verify_token, verified_token) VALUES (?, ?, ?, ?, ?, ?, ?);";
    /**
     * Deleting row SQL script of Verification entity
     */
    private static final String SQL_DELETE_RECORD = "DELETE FROM verification WHERE code = ?;";
    /**
     * Finding row by verify token SQL script of Verification entity
     */
    private static final String SQL_FIND_BY_VERIFY_TOKEN = "SELECT code, kind, token_code, token_text, json_data, verify_token, verified_token FROM verification WHERE verify_token = ? LIMIT 1;";
    /**
     * Finding row by verified token SQL script of Verification entity
     */
    private static final String SQL_FIND_BY_VERIFIED_TOKEN = "SELECT code, kind, token_code, token_text, json_data, verify_token, verified_token FROM verification WHERE verified_token = ? LIMIT 1;";
    /**
     * Finding row by code SQL script of Verification entity
     */
    private static final String SQL_FIND_BY_CODE = "SELECT code, kind, token_code, token_text, json_data, verify_token, verified_token FROM verification WHERE code = ? LIMIT 1;";

    private static VerificationDAO __instance;

    /**
     * Singleton implementation of DAO class of Verification entity
     * @return a DAO instance of Verification entity
     */
    public static VerificationDAO getInstance() {
        if (__instance == null) {
            __instance = new VerificationDAO();
        }
        return __instance;
    }

    /**
     * Delete Verification row by code
     * @param code a code of Verification
     * @return a DAO instance of Verification entity
     * @throws Exception is thrown when deleting Verification failed
     */
    public VerificationDAO delete(String code) throws Exception {
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
     * Find Verification row by ucode
     * @param code a code of Verification
     * @return a NULL if not found, Verification instance if found
     * @throws Exception is thrown when finding Verification failed
     */
    public Verification findByCode(String code) throws Exception {
        createIfNotExists();
        DatabaseConfig.getInstance();
        Connection connection = DatabaseConfig.getInstance().loadDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_CODE);
        preparedStatement.setString(1, code);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Verification target = parse(resultSet);
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
     * Find Verification row by verify token
     * @param verifyToken a verify token
     * @return a NULL if Verification row not found, a Verification instance if found
     * @throws Exception is thrown if finding Verification failed
     */
    public Verification findByVerifyToken(String verifyToken) throws Exception {
        createIfNotExists();
        DatabaseConfig.getInstance();
        Connection connection = DatabaseConfig.getInstance().loadDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_VERIFY_TOKEN);
        preparedStatement.setString(1, verifyToken);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Verification target = parse(resultSet);
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
     * Find Verification row by verified token
     * @param verifiedToken a verified token
     * @return a NULL if Verification row not found, a Verification instance if found
     * @throws Exception is thrown if finding Verification failed
     */
    public Verification findByVerifiedToken(String verifiedToken) throws Exception {
        createIfNotExists();
        DatabaseConfig.getInstance();
        Connection connection = DatabaseConfig.getInstance().loadDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_VERIFIED_TOKEN);
        preparedStatement.setString(1, verifiedToken);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Verification target = parse(resultSet);
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
     * Parse result set to Verification instance
     * @param resultSet a result set
     * @return a Verification instance
     * @throws Exception is thrown if parsing failed
     */
    public Verification parse(ResultSet resultSet) throws Exception {
        Verification target = new Verification();
        target.setCode(resultSet.getString(1));
        target.setKind(resultSet.getInt(2));
        target.setTokenCode(resultSet.getString(3));
        target.setTokenText(resultSet.getString(4));
        target.setJsonData(resultSet.getString(5));
        target.setVerifyToken(resultSet.getString(6));
        target.setVerifiedToken(resultSet.getString(7));
        return target;
    }

    /**
     * Inserting or updating Verification instance
     * @param data a Verification instance
     * @return a DAO of Verification entity
     * @throws Exception is thrown is upserting Verification failed
     */
    public VerificationDAO save(Verification data) throws Exception {
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
            preparedStatement.setString(2, data.getTokenCode());
            preparedStatement.setString(3, data.getTokenText());
            preparedStatement.setString(4, data.getJsonData());
            preparedStatement.setString(5, data.getVerifyToken());
            preparedStatement.setString(6, data.getVerifiedToken());
            preparedStatement.setString(7, data.getCode());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } else {
            resultSet.close();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(SQL_INSERT_RECORD);
            preparedStatement.setString(1, data.getCode());
            preparedStatement.setInt(2, data.getKind());
            preparedStatement.setString(3, data.getTokenCode());
            preparedStatement.setString(4, data.getTokenText());
            preparedStatement.setString(5, data.getJsonData());
            preparedStatement.setString(6, data.getVerifyToken());
            preparedStatement.setString(7, data.getVerifiedToken());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        return this;
    }

    /**
     * Create Verification table is not exists
     * @return a DAO instance of Verification entity
     */
    public VerificationDAO createIfNotExists() {
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

    /**
     * Check if Verification table exists or not
     * @return a true if Verification table exists, a false if not
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
}
