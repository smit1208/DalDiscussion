package com.macs.group6.daldiscussion.exceptions;

public class DAOException extends BaseException {
    public DAOException(ErrorCode code) {
        super(code);
    }
    public DAOException(String message, Throwable cause, ErrorCode code){
        super(message, cause, code);

    }

    public DAOException(String message, ErrorCode code) {
        super(message, code);

    }

    public DAOException(Throwable cause, ErrorCode code) {
        super(cause, code);

    }

}
