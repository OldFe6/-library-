package com.aliang.library.common.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class MPageNav {
    public MPage previous;
    public List<MPage> pages;
    public MPage next;
    public MPage first;
    public MPage max;
    public String currentPageIndex;
    public String offset;  // offset 对应着从哪一条记录开始返回
    public String limit; // limit 对应返回的记录条数

    private MPageNav() {

    }

    public MPageNav(MPage previous, List<MPage> pages, MPage next, MPage first, MPage max,
                    String currentPageIndex, String offset, String limit) {
        this.previous = previous;
        this.pages = pages;
        this.next = next;
        this.first = first;
        this.max = max;
        this.currentPageIndex = currentPageIndex;
        this.offset = offset;
        this.limit = limit;
    }

    public MPage getPrevious() {
        return previous;
    }

    public void setPrevious(MPage previous) {
        this.previous = previous;
    }

    public List<MPage> getPages() {
        return pages;
    }

    public void setPages(List<MPage> pages) {
        this.pages = pages;
    }

    public MPage getNext() {
        return next;
    }

    public void setNext(MPage next) {
        this.next = next;
    }

    public MPage getFirst() {
        return first;
    }

    public void setFirst(MPage first) {
        this.first = first;
    }

    public MPage getMax() {
        return max;
    }

    public void setMax(MPage max) {
        this.max = max;
    }

    public String getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(String currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
