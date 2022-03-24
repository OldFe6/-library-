package com.aliang.library.vo;

public class DoBookUpdateStatusResponse extends Response {
    private long bookId;
    private int status;
    private long id;
    private int bookCount;

    public DoBookUpdateStatusResponse() {
    }

    public DoBookUpdateStatusResponse(int code, String msg, long bookId, int status, long id, int bookCount) {
        super(code, msg);
        this.bookId = bookId;
        this.status = status;
        this.id = id;
        this.bookCount = bookCount;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }
}
