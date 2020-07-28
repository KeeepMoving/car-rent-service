package com.lz.crs.data;

public enum ResponseCodeEnum {

    SUCCESS(0, "ok"),
    INTERNAL_SERVER_ERROR(1000, "internal server error"),
    INSUFFICIENT_STOCK(1001, "insufficient stock");

    private int status;
    private String msg;

    ResponseCodeEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMsg() {
        return this.msg;
    }

}
