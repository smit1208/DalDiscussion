package com.macs.group6.daldiscussion.entities;

public class Verification {
    public static final int KIND_FORGOT_PASSWORD = 1;

    public String code = "";
    public int kind = KIND_FORGOT_PASSWORD;
    public String tokenCode = "";
    public String tokenText = "";
    public String jsonData = "{}";
    public String verifyToken = "";
    public String verifiedToken = "";

}
