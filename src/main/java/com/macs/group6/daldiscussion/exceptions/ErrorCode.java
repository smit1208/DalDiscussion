package com.macs.group6.daldiscussion.exceptions;

public enum  ErrorCode {

    INSERT_INTO_DB_ERROR("300"),
    FILE_IO_ERROR("301"),
    RETRIVE_FROM_DB_ERROR("302"),
    UPDATE_RECORD_DB_ERROR("303"),
    DELETE_RECORD_DB_ERROR("304"),
    // Registration Error
    EMAIL_IN_USE("AlreadyInUSe.Form.email"),
    EMAIL_NOT_FOUND("NotFound.loginForm.email"),
    EMAIL_NOT_VALID("Invalid.Form.email"),
    AUTHENTICATION_ERROR("Authentication.Form.password"),

    DUPLICATE_EMAIL("Duplicate.registrationForm.email"),
    PASSWORD_DOES_NOT_MATCH("Diff.registrationForm.passwordConfirm"),
    PASSWORD_MIN_SIZE_ERROR("MinSize.registrationForm.password"),
    PASSWORD_MAX_SIZE_ERROR("MaxSize.registrationForm.password"),
    PASSWORD_POLICY_DOES_NOT_SATISFY("PasswordPolicy.registrationForm.passwordConfirm"),
    PASSWORD_SIZE_POLICY("Size.registrationForm.password"),

    // mandatory Field
    NOTEMPTY("NotEmpty");

    private final String propertyName;

    ErrorCode(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
