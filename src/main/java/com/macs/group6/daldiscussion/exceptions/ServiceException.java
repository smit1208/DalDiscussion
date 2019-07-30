package com.macs.group6.daldiscussion.exceptions;

public class ServiceException extends BaseException {
    public ServiceException(ErrorCode code) {
        super(code);
    }

    public ServiceException(String message, Throwable cause, ErrorCode code) {
        super(message, cause, code);
    }

    public ServiceException(String message, ErrorCode code) {
        super(message, code);
    }

    public ServiceException(Throwable cause, ErrorCode code) {
        super(cause, code);
    }
}
