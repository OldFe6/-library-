package com.aliang.library.common;


import com.aliang.library.common.model.MPage;
import com.aliang.library.common.model.MPageNav;

import java.util.ArrayList;
import java.util.List;

public class PageNavUtil {
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGES = 10;
    public static final String DEFAULT_PAGE_REQUEST_FORMAT = "%s?page=%d&pageSize=%d";
    public static final String DEFAULT_PAGE_REQUEST_FORMAT_WITH_PARAM = "%s&page=%d&pageSize=%d";

    /**
     * 根据需要查询的页码currentPageIndexStr、每页的记录数pageSizeStr以及总记录数records，
     * 生成分页导航中各个页面的超链接
     *
     * @param baseUrl             请求的地址，可以包含参数，比如"user_show_car?type=price&order=asc"；
     *                            也可以不包含参数，比如"user_show_car"；
     *                            但必须注意的时，baseUrl不能包含空格！！！
     * @param currentPageIndexStr 当前请求的页号，从1开始计数。比如，前端通过"user_show_car?page=5"请求5号页面的数据，
     *                            那么此时的currentPageIndexStr就是5。
     * @param pageSizeStr         每页的记录数，如果传入null的话，那么每页的记录数就是默认值DEFAULT_PAGE_SIZE，也就是每页10条记录。
     * @param records             总的记录数。比如通过“select count(*) from table001 [where condition];”查询所得的总记录行数。
     * @return MPageNav           导航对象，包含了计算出来的分页导航的各种相关信息。
     */
    public static MPageNav genMPageNav(final String baseUrl,
                                       final String currentPageIndexStr,
                                       final String pageSizeStr,
                                       final int records) {
        if (baseUrl == null) {
            throw new IllegalArgumentException("baseUrl is null");
        }
        int currentPageIndex = currentPageIndexStr == null ? 1 : Integer.parseInt(currentPageIndexStr);
        currentPageIndex = currentPageIndex <= 0 ? 1 : currentPageIndex;
        int pageSize = pageSizeStr == null ? DEFAULT_PAGE_SIZE : Integer.parseInt(pageSizeStr);
        pageSize = pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize;
        int startOffset = (currentPageIndex - 1) * pageSize;

        int maxPages = (int) Math.ceil(records / (float) pageSize);
        maxPages = maxPages <= 0 ? 1 : maxPages;
        List<MPage> pages = new ArrayList<>();
        int pageStart = (currentPageIndex - 1) / DEFAULT_PAGES * DEFAULT_PAGES + 1;
        if (pageStart > maxPages) {
            pageStart = maxPages;
        }
        int pageEnd = pageStart + DEFAULT_PAGES;  // [pageStart, pageEnd)
        if (pageEnd > maxPages + 1) {
            pageEnd = maxPages + 1;
        }

        String formatBaseURL = baseUrl.contains("?") ?
                DEFAULT_PAGE_REQUEST_FORMAT_WITH_PARAM : DEFAULT_PAGE_REQUEST_FORMAT;

        // [pageStart, pageEnd)，前闭后开
        for (int i = pageStart; i < pageEnd; i++) {
            pages.add(new MPage(String.valueOf(i), String.format(formatBaseURL, baseUrl, i, pageSize)));
        }
        MPage previousPage;
        if (currentPageIndex <= 1) {
            previousPage = new MPage(String.valueOf(1), String.format(formatBaseURL, baseUrl, 1, pageSize));
        } else {
            previousPage = new MPage(String.valueOf(currentPageIndex - 1),
                    String.format(formatBaseURL, baseUrl, currentPageIndex - 1, pageSize));
        }
        MPage nextPage;
        if (currentPageIndex >= maxPages) {
            nextPage = new MPage(String.valueOf(maxPages),
                    String.format(formatBaseURL, baseUrl, maxPages, pageSize));
        } else {
            nextPage = new MPage(String.valueOf(currentPageIndex + 1),
                    String.format(formatBaseURL, baseUrl, currentPageIndex + 1, pageSize));
        }

        MPage firstPage = new MPage(String.valueOf(1), String.format(formatBaseURL, baseUrl, 1, pageSize));
        MPage maxPage = new MPage(String.valueOf(maxPages),
                String.format(formatBaseURL, baseUrl, maxPages, pageSize));
        return new MPageNav(previousPage, pages, nextPage, firstPage, maxPage,
                String.valueOf(currentPageIndex), String.valueOf(startOffset), String.valueOf(pageSize));
    }

    public static void main(String[] args) {
        MPageNav mPageNav = genMPageNav("user_show_car?type=price&order=asc", "5", "10", 73);
        System.out.println(mPageNav);
        MPageNav mPageNav2 = genMPageNav("user_show_car", "2", "10", 7);
        System.out.println(mPageNav2);
    }
}
