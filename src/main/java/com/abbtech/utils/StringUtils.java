package com.abbtech.utils;

public final class StringUtils {

    public static final String EMPTY_STRING = "";

    private StringUtils() {
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String[] getArray(String str, String split) {
        return str.split(split);
    }

}
