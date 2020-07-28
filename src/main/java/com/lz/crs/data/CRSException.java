package com.lz.crs.data;

public class CRSException extends Exception {

    private ResponseCodeEnum errorCode;
    private String errorMessage;

    public CRSException() {
        super();
    }

    public CRSException(ResponseCodeEnum errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;

    }

    public ResponseCodeEnum getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage () {
        return this.errorMessage;
    }
}
