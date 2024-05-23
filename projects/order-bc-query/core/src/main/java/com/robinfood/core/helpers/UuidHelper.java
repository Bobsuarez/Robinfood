package com.robinfood.core.helpers;

import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;

public final class UuidHelper {

    private static final Long RANGE_NUM = 1_606_111_999_999L;
    private static final Integer PAD_SIZE = 5;
    private static final Integer FROM_BASE_CONVERT = 10;
    private static final Integer TO_BASE_CONVERT = 36;
    private static final String PAD_STRING = "0";
    private static final String CHARACTER_NOT_VALID = "-";

    private UuidHelper() {}

    public static String getByLong(Long inputNumber) {
        Long milliseconds = System.nanoTime() - RANGE_NUM;
        String posIdFormatted = StringUtils.leftPad(
                inputNumber.toString(),
                PAD_SIZE,
                PAD_STRING
        );

        return new BigInteger(
                milliseconds + posIdFormatted,
                FROM_BASE_CONVERT
        )
            .toString(TO_BASE_CONVERT)
            .replace(CHARACTER_NOT_VALID, "");
    }
}
