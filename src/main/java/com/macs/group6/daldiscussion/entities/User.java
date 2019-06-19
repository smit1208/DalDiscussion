package com.macs.group6.daldiscussion.entities;

/**
 * Entity class for User.
 * @author Kush Rao
 */
public class User {
    /**
     * Value of Admin user kind
     */
    public static final int KIND_ADMIN = 1;
    /**
     * Value of Normal user kind
     */
    public static final int KIND_NORMAL = 2;

    private String _code = "";
    private int _kind = KIND_NORMAL;
    private String _username = "";
    private String _password = "";
    private String _firstName = "";
    private String _lastName = "";
    private String _middleName = "";
    private String _email = "";

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public String getMiddleName() {
        return _middleName;
    }

    public void setMiddleName(String middleName) {
        _middleName = middleName;
    }

    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String lastName) {
        _lastName = lastName;
    }

    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String firstName) {
        _firstName = firstName;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public String getUsername() {
        return _username;
    }

    public void setUsername(String username) {
        _username = username;
    }

    public int getKind() {
        return _kind;
    }

    public void setKind(int kind) {
        _kind = kind;
    }

    public String getCode() {
        return _code;
    }

    public void setCode(String code) {
        _code = code;
    }
}
