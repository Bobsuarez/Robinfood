package com.robinfood.core.enums.logs;

import org.apache.commons.lang3.StringUtils;

public enum OrderWarningLogEnum {

    WARN_ID_CHANNEL_NOT_FOUND("4000", "Error obtain channel info with channel_id {}");

    private final String code;
    private final String message;

    OrderWarningLogEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return String.format(this.code, StringUtils.SPACE, this.message);
    }
}
