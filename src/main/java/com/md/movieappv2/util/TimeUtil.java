package com.md.movieappv2.util;

public class TimeUtil {

    public static String getTimeString(int duration) {
        return (duration / 60) + "h " + (duration % 60) + "m";
    }
}