/*@author Sharon */
/*Referenced from https://stackify.com/java-custom-exceptions/*/
package com.macs.group6.daldiscussion.exceptions;

public class BaseException extends Exception {
    private final ErrorCode code;

    public BaseException(ErrorCode code){
        super();
        this.code = code;
    }

    public BaseException(String message, Throwable cause, ErrorCode code){
        super(message, cause);
        this.code = code;
    }

    public BaseException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    public BaseException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
    }

    public ErrorCode getCode() {
        return this.code;
    }
}
