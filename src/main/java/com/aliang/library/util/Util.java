package com.aliang.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static boolean isDigital(String s) {
        return s.matches("[0-9]*");
    }

    public static boolean isDouble(String s) {
        return s.matches("\\d+(\\.\\d+)?");
    }

    public static Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(String.format("failed to parseDate[%s]", dateString), e);
        }
    }

    public static String today() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }


    public static String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static void main(String[] args) {
        System.out.println(isDouble("54"));
        System.out.println(isDouble(""));
        System.out.println(isDouble("1ab"));
        System.out.println(isDouble("1.2"));
    }
}
