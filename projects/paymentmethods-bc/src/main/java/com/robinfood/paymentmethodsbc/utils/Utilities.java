package com.robinfood.paymentmethodsbc.utils;

import java.text.Normalizer;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

import static com.robinfood.paymentmethodsbc.constants.AppConstants.CRITICAL_DAY_REGEX;
import static com.robinfood.paymentmethodsbc.constants.CreditCardConstants.CREDIT_CARD_MAX_SIZE;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public final class Utilities {

    private Utilities() {}

    /**
     * @param map
     * @return String
     */
    public static String convertWithStream(Map<String, String> map) {
        return map
            .keySet()
            .stream()
            .map(key -> key + "=" + map.get(key))
            .collect(Collectors.joining(", ", "{", "}"));
    }

    public static String longTimeStampToDateTimeFormat(
        Long timestamp,
        String pattern,
        String timeZone
    ) {
        LocalDateTime localDate = Instant
            .ofEpochMilli(timestamp)
            .atZone(ZoneId.of(timeZone.trim()))
            .toLocalDateTime();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return dtf.format(localDate);
    }

    public static String localDateTimeToDateTimeFormat(
        Long timestamp,
        String pattern
    ) {
        LocalDateTime localDate = Instant
            .ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return dtf.format(localDate);
    }

    public static String localDateTimeToDateTimeFormat(
        LocalDateTime timestamp,
        String pattern
    ) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return dtf.format(timestamp);
    }

    public static LocalDateTime stringToLocalDateTime(
        String date,
        String pattern
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime
            .parse(date, formatter)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }

    public static LocalDateTime stringToLocalDateTime(
        String date,
        String pattern,
        String timeZone
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime
            .parse(date, formatter)
            .atZone(ZoneId.of(timeZone.trim()))
            .toLocalDateTime();
    }

    public static Long stringToLong(String text) {
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String getStringLastChars(String text, int length) {
        int ini = text.length() - length;
        return text.substring(ini, ini + length);
    }

    public static String maskCreditCardNumber(String text, int length) {
        return (
            "*".repeat(text.length() - length) +
            getStringLastChars(text, length)
        );
    }

    public static String creditCardNumberIsGreaterThanFour(String text, int length) {
        if (text.length() > CREDIT_CARD_MAX_SIZE) {
            return text;
        } else {
            return maskCreditCardNumber(text, length);
        }
    }

    public static String removeDiacritics(String originalText) {
        return Normalizer
            .normalize(originalText, Normalizer.Form.NFD)
            .replaceAll(CRITICAL_DAY_REGEX, EMPTY);
    }
}
