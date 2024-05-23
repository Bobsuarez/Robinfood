package com.robinfood.enums;

import java.util.Objects;
import java.util.regex.Pattern;

public enum ErrorLogsEnum {

    ERROR_APP_EXCEPTION("502", "%s, %s, %s"),

    ERROR_SENT_SQS("503", "No message was sent to the SQS"),

    ERROR_DATA_NOT_FOUND("504", "Not found body"),
    ERROR_RESPONSE_HTTP("0508", "Response HTTP unsuccessfully URL: __COMPLEMENT__ , exception : {}"),
    ERROR_RESPONSE_HTTP_BODY_NULL("0509",
            "Response HTTP URL: __COMPLEMENT__ , exception : body is null or empty"),
    ERROR_EXCEPTION_URL_NOT_FOUND_PROPERTY("0510",
            "[ReplicateEventUseCase] --> invoke ApplicationException : __COMPLEMENT__"),
    ERROR_EXCEPTION_THREAD_TIMEOUT("0512",
            "[ReplicateEventUseCase] --> invoke thread timeout : __COMPLEMENT__");
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

    public String getMessageWithCode() {
        return code + " " + message;
    }

    public String getMessageWithCodeWithComplement(Object complement) {
        return code + " " + replaceString(this.message, complement);
    }

    private String replaceString(String objectReplace, Object complement) {

        if (Objects.nonNull(complement)) {
            return objectReplace.replaceAll(compilePattern.toString(), complement.toString());
        }

        return objectReplace.replaceAll(compilePattern.toString(), "");
    }
}
