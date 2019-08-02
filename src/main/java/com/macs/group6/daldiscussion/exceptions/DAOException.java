package com.macs.group6.daldiscussion.exceptions;
/*
@author Sharon Alva
*/
public class DAOException extends Exception {

    public DAOException(String message, Throwable cause){
        super(message, cause);

    }

    public DAOException(String message) {
        super(message);

    }

    public DAOException(Throwable cause) {
        super(cause);

    }

}
