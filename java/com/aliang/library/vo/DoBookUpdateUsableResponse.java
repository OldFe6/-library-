package com.aliang.library.vo;

public class DoBookUpdateUsableResponse extends Response {
    private String bookId;
    private String usable;

    public DoBookUpdateUsableResponse() {
    }

    public DoBookUpdateUsableResponse(int code, String msg, String bookId, String usable) {
        super(code, msg);
        this.bookId = bookId;
        this.usable = usable;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUsable() {
        return usable;
    }

    public void setUsable(String usable) {
        this.usable = usable;
    }
}
