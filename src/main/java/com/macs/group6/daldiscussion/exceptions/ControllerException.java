package com.macs.group6.daldiscussion.exceptions;
/*
@author Sharon Alva
*/
public class ControllerException extends Exception {

    public ControllerException(String message, Throwable cause
    ) {
        super(message, cause);
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }
}
