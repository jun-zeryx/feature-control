package com.moneylion.assessment.exception;

public class CommonException extends Exception {

    CommonErrorCode errorCode;

    public CommonException(CommonErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public CommonErrorCode getErrorCode() {
        return errorCode;
    }
}
