package com.aliang.library.model;

public class MRecordSearch {
    private String bookId;
    private String bookName;
    private String userId;

    public MRecordSearch() {
        this("", "","");
    }

    public MRecordSearch(String bookId, String bookName, String userId) {
        this.bookId = bookId == null ? "" : bookId;
        this.bookName = bookName == null ? "" : bookName;
        this.userId = userId == null ? "" : userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
