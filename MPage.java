package com.aliang.library.common.model;

public class MPage {
    public String index;
    public String url;

    private MPage() {

    }

    public MPage(String index, String url) {
        this.index = index;
        this.url = url;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
