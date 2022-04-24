package com.aliang.library.vo;

public class DoBookDeleteResponse extends Response {
    private String bookId;

    public DoBookDeleteResponse() {
    }


    public DoBookDeleteResponse(int code, String msg, String bookId) {
        super(code, msg);
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
