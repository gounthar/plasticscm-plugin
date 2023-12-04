package com.codicesoftware.plugins.hudson.util;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Util;

public class StringUtil {

    public static final String SEPARATOR = "/";

    private StringUtil() { }

    public static int tryParse(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static String singleLine(String value) {
        return Util.fixNull(value).replaceAll("[\\n\\r\\t ]+", " ").trim();
    }

    public static String ensureStartsWithSlash(@NonNull final String scriptPath) {
        return scriptPath.startsWith(SEPARATOR) ? scriptPath : SEPARATOR + scriptPath;
    }
}
