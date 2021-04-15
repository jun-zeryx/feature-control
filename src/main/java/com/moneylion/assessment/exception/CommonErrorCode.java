package com.moneylion.assessment.exception;

import org.springframework.http.HttpStatus;

public enum CommonErrorCode {

    USER_NOT_EXIST("1001", "User does not exist", HttpStatus.BAD_REQUEST),
    UPDATE_FAILED("1002", "User not updated", HttpStatus.NOT_MODIFIED),
    FEATURE_NOT_SET("1003","Feature has not been set", HttpStatus.BAD_REQUEST),
    MISSING_REQUIRED_FIELD("1004","Required field is missing", HttpStatus.BAD_REQUEST);

    private String code;
    private String message;
    private HttpStatus httpStatus;

    CommonErrorCode(final String code, final String message, final HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
