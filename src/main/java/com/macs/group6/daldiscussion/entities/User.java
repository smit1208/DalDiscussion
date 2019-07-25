package com.macs.group6.daldiscussion.entities;

/**
 * Entity class for User.
 * @author Kush Rao
 */
public class User {
    private int _id;
    private String _firstName;
    private String _lastName;


    private String _email;
    private String _password;
    private String _confirmPassword;
    private int _karmaPoints;
    private int _subscriptionLimit;
    private int _currentStatus;
    private int role;

    public int getCurrentStatus() {
        return _currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        _currentStatus = currentStatus;
    }

    public int getSubscriptionLimit() {
        return _subscriptionLimit;
    }

    public void setSubscriptionLimit(int subscriptionLimit) {
        _subscriptionLimit = subscriptionLimit;
    }

    public int getKarmaPoints() {
        return _karmaPoints;
    }

    public void setKarmaPoints(int karmaPoints) {
        _karmaPoints = karmaPoints;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
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

    public String getConfirmPassword() {
        return _confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        _confirmPassword = confirmPassword;
    }


 /*   public String getConfirmPassword() {
        return _confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        _confirmPassword = confirmPassword;
    }*/

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
