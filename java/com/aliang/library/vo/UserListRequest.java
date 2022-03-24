package com.aliang.library.vo;

public class UserListRequest {
    private String userId;
    private String username;
    private String account;

    // 从客户端请求里，读取出分页相关的参数。其中：
    // page代表查询的是第几个分页，从1开始：
    // pageSize代表的是每个分页的记录行数
    private String page;
    private String pageSize;

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

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
