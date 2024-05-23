package com.robinfood.enums;

import lombok.Getter;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
public enum ErrorLogsEnum {

    ERROR_APP_EXCEPTION("501", "%s, %s, %s"),

    ERROR_SECURITY_EXCEPTION("502", "Message Error Token: %s"),

    ERROR_EXCEPTION_TOKEN("503", "Message Error Token: %s  Caused By: %s"),

    ERROR_RESPONSE_HTTP("504", "Response HTTP unsuccessfully URL: __COMPLEMENT__"),

    ERROR_RESPONSE_HTTP_BODY_NULL("505", "Response HTTP URL: __COMPLEMENT__ , exception : body is null or empty"),

    ERROR_RESPONSE_CLIENT_ASYNC("506", "Asynchronous message not sent");

    private static final Pattern compilePattern = Pattern.compile("__COMPLEMENT__");
    private final String code;
    private final String message;

    ErrorLogsEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String replaceComplement(Object complement) {
        return replaceString(this.message, complement);
    }

    private String replaceString(String objectReplace, Object complement) {
        if (Objects.nonNull(complement)) {
            return objectReplace.replaceAll(compilePattern.toString(), complement.toString());
        }

        return objectReplace.replaceAll(compilePattern.toString(), "");
    }

    public String getMessageWithCode() {
        return code + " " + message;
    }

    public String getMessageWithCodeWithComplement(Object complement) {
        return code + " " + replaceString(this.message, complement);
    }
}
