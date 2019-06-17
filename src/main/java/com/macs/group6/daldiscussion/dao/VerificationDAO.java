package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.entities.Verification;
import database.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

public class VerificationDAO {
    private static final String SQL_CREATE_TABLE = "CREATE TABLE verification (code VARCHAR(36) PRIMARY KEY, kind INT NOT NULL, token_code VARCHAR(200) NOT NULL, token_text VARCHAR(200) NOT NULL, json_data VARCHAR(8000) NOT NULL, verify_token VARCHAR(36) NOT NULL, verified_token VARCHAR(36) NOT NULL);";
    private static final String SQL_TABLE_EXISTS = "SELECT code, kind, token_code, token_text, json_data, verify_token, verified_token FROM verification LIMIT 1;";

    private static final String SQL_RECORD_EXISTS = "SELECT code from verification WHERE code = ? LIMIT 1;";
    private static final String SQL_UPDATE_RECORD = "UPDATE verification SET kind = ?, token_code = ?, token_text = ?, json_data = ?, verify_token = ?, verified_token = ? WHERE code = ?;";
    private static final String SQL_INSERT_RECORD = "INSERT INTO verification (code, kind, token_code, token_text, json_data, verify_token, verified_token) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_DELETE_RECORD = "DELETE FROM verification WHERE code = ?;";
    private static final String SQL_FIND_BY_VERIFY_TOKEN = "SELECT code, kind, token_code, token_text, json_data, verify_token, verified_token FROM verification WHERE verify_token = ? LIMIT 1;";
    private static final String SQL_FIND_BY_VERIFIED_TOKEN = "SELECT code, kind, token_code, token_text, json_data, verify_token, verified_token FROM verification WHERE verified_token = ? LIMIT 1;";
    private static final String SQL_FIND_BY_CODE = "SELECT code, kind, token_code, token_text, json_data, verify_token, verified_token FROM verification WHERE code = ? LIMIT 1;";

    private static VerificationDAO __instance;

    public static VerificationDAO instance() {
        if (__instance == null) {
            __instance = new VerificationDAO();
        }
        return __instance;
    }

    public VerificationDAO delete(String code) throws Exception {
        Connection connection = DatabaseConfig.getInstance().loadDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_RECORD);
        preparedStatement.setString(1, code);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return this;
    }

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

    public Verification parse(ResultSet resultSet) throws Exception {
        Verification target = new Verification();
        target.code = resultSet.getString(1);
        target.kind = resultSet.getInt(2);
        target.tokenCode = resultSet.getString(3);
        target.tokenText = resultSet.getString(4);
        target.jsonData = resultSet.getString(5);
        target.verifyToken = resultSet.getString(6);
        target.verifiedToken = resultSet.getString(7);
        return target;
    }

    public VerificationDAO save(Verification data) throws Exception {
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
            preparedStatement.setString(2, data.tokenCode);
            preparedStatement.setString(3, data.tokenText);
            preparedStatement.setString(4, data.jsonData);
            preparedStatement.setString(5, data.verifyToken);
            preparedStatement.setString(6, data.verifiedToken);
            preparedStatement.setString(7, data.code);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } else {
            resultSet.close();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(SQL_INSERT_RECORD);
            preparedStatement.setString(1, data.code);
            preparedStatement.setInt(2, data.kind);
            preparedStatement.setString(3, data.tokenCode);
            preparedStatement.setString(4, data.tokenText);
            preparedStatement.setString(5, data.jsonData);
            preparedStatement.setString(6, data.verifyToken);
            preparedStatement.setString(7, data.verifiedToken);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        return this;
    }

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
