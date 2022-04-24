package com.aliang.library.vo;

public class DoBookLendResponse extends Response {
    private long bookId;
    private long userId;

    public DoBookLendResponse() {
    }


    public DoBookLendResponse(int code, String msg, long bookId, long userId) {
        super(code, msg);
        this.bookId = bookId;
        this.userId = userId;

    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
