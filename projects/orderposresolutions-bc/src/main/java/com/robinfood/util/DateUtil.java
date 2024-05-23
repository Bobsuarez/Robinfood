package com.robinfood.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static LocalDateTime parseFull(String time) {
        try {
            return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception ignored) {
        }
        try {
            return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        } catch (Exception ignored) {
        }
        try {
            return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        } catch (Exception ignored) {
        }
        try {
            return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0000"));
        } catch (Exception ignored) {
        }
        try {
            return LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        } catch (Exception ignored) {
        }
        return LocalDateTime.parse(time);
    }

    public static String formatStandard(LocalDateTime date) {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatDate);
    }
}
