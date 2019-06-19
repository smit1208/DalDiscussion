package com.macs.group6.daldiscussion.model;

import java.io.PrintWriter;
import java.io.StringWriter;

public class BasicResponse {
    private boolean _isError = false;
    private String _errorCode = "";
    private String _errorMessage = "";
    private String _usercode = "";

    public boolean getIsError() {
        return _isError;
    }

    public void setIsError(boolean isError) {
        _isError = isError;
    }

    public String getErrorCode() {
        return _errorCode;
    }

    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;
    }

    public String getErrorMessage() {
        return _errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        _errorMessage = errorMessage;
    }

    public String getUsercode() {
        return _usercode;
    }

    public void setUsercode(String usercode) {
        _usercode = usercode;
    }

    public BasicResponse setError(String errorCode, String errorMessage) {
        _isError = true;
        _errorCode = errorCode;
        _errorMessage = errorMessage;
        return this;
    }

    public BasicResponse setError(String errorCode, Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        setError(errorCode, sw.toString());
        return this;
    }
}
