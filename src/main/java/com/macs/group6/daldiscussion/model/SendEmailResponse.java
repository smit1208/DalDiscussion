package com.macs.group6.daldiscussion.model;

import java.io.PrintWriter;
import java.io.StringWriter;

public class SendEmailResponse {
    public boolean isError = false;
    public String errorCode = "";
    public String errorMessage = "";
    public String sentEmail = "";
    public String sentName = "";
    public String sentSubject = "";
    public String sentBodyText = "";
    public String sentBodyHtml = "";

    public SendEmailResponse setError(String errorCode, String errorMessage) {
        this.isError = true;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        return this;
    }

    public SendEmailResponse setError(String errorCode, Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        this.setError(errorCode, sw.toString());
        return this;
    }
}
