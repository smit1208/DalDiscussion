package com.macs.group6.daldiscussion.entities;

/**
 * This entity class contains data for verifications in application.
 * @author Kush Rao
 */
public class Verification {
    /**
     * Value for forgot password verification
     */
    public static final int KIND_FORGOT_PASSWORD = 1;

    private String _code = "";
    private int _kind = KIND_FORGOT_PASSWORD;
    private String _tokenCode = "";
    private String _tokenText = "";
    private String _jsonData = "{}";
    private String _verifyToken = "";
    private String _verifiedToken = "";

    /**
     * Get token for verified process
     * @return a verified token
     */
    public String getVerifiedToken() {
        return _verifiedToken;
    }

    /**
     * Set token for verified process
     * @param verifiedToken a verified token
     */
    public void setVerifiedToken(String verifiedToken) {
        _verifiedToken = verifiedToken;
    }

    /**
     * Get a token used for verifying process
     * @return a token
     */
    public String getVerifyToken() {
        return _verifyToken;
    }

    /**
     * Set a token used for verifying process
     * @param verifyToken a token
     */
    public void setVerifyToken(String verifyToken) {
        _verifyToken = verifyToken;
    }

    /**
     * Get data (in JSON string) used in verifying process
     * @return a JSON string of data
     */
    public String getJsonData() {
        return _jsonData;
    }

    /**
     * Set data (in JSON string) used in verifying process
     * @param jsonData a JSON string of data
     */
    public void setJsonData(String jsonData) {
        _jsonData = jsonData;
    }

    /**
     * Get token text used in verifying process
     * @return a token text
     */
    public String getTokenText() {
        return _tokenText;
    }

    /**
     * Set token text used in verifying process
     * @param tokenText a token text
     */
    public void setTokenText(String tokenText) {
        _tokenText = tokenText;
    }

    /**
     * Get token code used in verifying process. In Forgot Password Verification, it contains code of current user.
     * @return a token code
     */
    public String getTokenCode() {
        return _tokenCode;
    }

    /**
     * Set token code used in verifying process. In Forgot Password Verification, it contains code of current user.
     * @param tokenCode a token code
     */
    public void setTokenCode(String tokenCode) {
        _tokenCode = tokenCode;
    }

    public String getCode() {
        return _code;
    }

    public void setCode(String code) {
        _code = code;
    }

    public int getKind() {
        return _kind;
    }

    public void setKind(int kind) {
        _kind = kind;
    }
}
