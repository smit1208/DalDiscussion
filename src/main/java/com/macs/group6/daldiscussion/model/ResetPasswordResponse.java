package com.macs.group6.daldiscussion.model;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ResetPasswordResponse {
    public boolean isError = false;
    public String errorCode = "";
    public String errorMessage = "";

    public ResetPasswordResponse setError(String errorCode, String errorMessage) {
        this.isError = true;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        return this;
    }

    public ResetPasswordResponse setError(String errorCode, Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        this.setError(errorCode, sw.toString());
        return this;
    }
}
