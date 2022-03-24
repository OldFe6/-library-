package com.aliang.library.model;

public class MBookSearch {
    private String author;
    private String bookName;
    private String priceOrder;

    public MBookSearch() {
        this("", "","");
    }

    public MBookSearch(String author, String bookName, String priceOrder) {
        this.author = author == null ? "" : author;
        this.bookName = bookName == null ? "" : bookName;
        this.priceOrder = priceOrder == null ? "" : priceOrder;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(String priceOrder) {
        this.priceOrder = priceOrder;
    }
}
