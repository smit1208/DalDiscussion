package com.macs.group6.daldiscussion.entities;

public class User {
    public static final int KIND_ADMIN = 1;
    public static final int KIND_NORMAL = 2;

    public String code = "";
    public int kind = KIND_NORMAL;
    public String username = "";
    public String password = "";
    public String firstName = "";
    public String lastName = "";
    public String middleName = "";
    public String email = "";

}
