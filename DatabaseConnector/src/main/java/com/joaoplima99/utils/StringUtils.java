package com.joaoplima99.utils;

public final class StringUtils {

    @Deprecated(since = "1.0")
    private StringUtils() {}

    public static String getAppendedCharString(char c, int n) {
        if(n < 1) throw new UnsupportedOperationException("Number of concatenations must be at least one.");
        StringBuilder sBuilder = new StringBuilder();
        int i = 0;
        while(i < n) {
            sBuilder.append(c);
            i++;
        }
        return sBuilder.toString();
    }

    public static void emptyStringBuilder(StringBuilder sBuilder) {
        sBuilder.delete(0, sBuilder.length());
    }
}