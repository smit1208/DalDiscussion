package com.macs.group6.daldiscussion.exceptions;

public class ControllerException extends BaseException {
    public ControllerException(ErrorCode code) {
        super(code);
    }

    public ControllerException(String message, Throwable cause, ErrorCode code) {
        super(message, cause, code);
    }

    public ControllerException(String message, ErrorCode code) {
        super(message, code);
    }

    public ControllerException(Throwable cause, ErrorCode code) {
        super(cause, code);
    }
}
