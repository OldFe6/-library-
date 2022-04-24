package com.aliang.library.util;

import java.util.Scanner;

public class ScannerUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String next() {
        return scanner.next();
    }

    public static int nextInt() {
        return scanner.nextInt();
    }

    public static void closeScanner() {
        scanner.close();
    }
}
