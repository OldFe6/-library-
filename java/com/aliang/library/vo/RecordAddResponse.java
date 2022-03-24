package com.aliang.library.vo;

public class RecordAddResponse extends Response {
    private long bookId;
    private long userId;

    public RecordAddResponse() {
    }

    public RecordAddResponse(int code, String msg, long bookId, long userId) {
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
