package com.aliang.library.model;

public class MUserSearch {
    private String userId;
    private String username;
    private String account;

    public MUserSearch() {
        this("", "", "");
    }

    public MUserSearch(String userId, String username, String account) {
        this.userId = userId == null ? "" : userId;
        this.username = username == null ? "" : username;
        this.account = account == null ? "" : account;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
