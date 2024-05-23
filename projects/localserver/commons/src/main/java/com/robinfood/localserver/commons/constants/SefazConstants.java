package com.robinfood.localserver.commons.constants;

import org.springframework.stereotype.Component;

@Component
public final class SefazConstants {

    public static final String PERCENTAGE_PREFIX="p";
    public static final String VALUTAXE_PREFIX="vBC";
    public static final String PERCENTAGE_PATTERN_ICMS="0.00";
    public static final String PERCENTAGE_PATTERN_OTHER="0.0000";
    public static final String ICMS_NAMES="ICMS";
    public static final String VALUE_SEFAZ_PRODUCT_PATTERN="0.00#";
    public static final String VALUE_PATTERN="0.00";
    public static final Long   MAIN_DISH_CFPO=5101L;
    public static final Long   DESSERT_CFPO=5102L;
    public static final Long   DRINK_CFPO=5405L;
    public static final String CFOP_NAME="CFOP";
    public static final String CODE_NAME="code";
    public static final int PERCENT_DIVISION=100;
    public static final int ROUNDING_SCALE=2;
    public static final int RANDOM_BOUND=1000;
    public static final int ZERO=0;
    public static final String COUNTRY ="pt-br";

    private SefazConstants() {
    }
}
