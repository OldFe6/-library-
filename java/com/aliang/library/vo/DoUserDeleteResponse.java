package com.aliang.library.vo;

public class DoUserDeleteResponse extends Response {

    private String userId;

    public DoUserDeleteResponse() {
    }

    public DoUserDeleteResponse(int code, String msg, String userId) {
        super(code, msg);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
