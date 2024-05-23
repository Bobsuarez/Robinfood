package com.robinfood.enums;

import lombok.Getter;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
public enum ErrorLogsEnum {

    ERROR_CONNECTION("0501", "Failed to connect to the database"),

    ERROR_APP_EXCEPTION("0502", "%s, %s, %s"),

    ERROR_DATA_NOT_FOUND("0503", "Data not found"),

    ERROR_URL_NOT_FOUND("0504", "Data not found __COMPLEMENT__"),

    ERROR_RESPONSE_HTTP("0508", "Response HTTP unsuccessfully URL: __COMPLEMENT__ , exception : {}"),

    ERROR_RESPONSE_HTTP_BODY_NULL("0509",
            "Response HTTP URL: __COMPLEMENT__ , exception : body is null or empty"),

    ERROR_RESPONSE_CLIENT_ASYNC("0510", "Asynchronous message not sent"),

    ERROR_EXCEPTION_URL_NOT_FOUND_PROPERTY("0511",
            "[ReplicateEventUseCase] --> invoke ApplicationException : __COMPLEMENT__"),

    ERROR_EXCEPTION_THREAD_INTERRUPTED_EXCEPTION("0512",
            "[ReplicateEventUseCase] --> invoke thread InterruptedException : __COMPLEMENT__ "),

    ERROR_EXCEPTION_THREAD_EXECUTION_EXCEPTION("0513",
            "[ReplicateEventUseCase] --> invoke thread ExecutionException : __COMPLEMENT__"),

    ERROR_EXCEPTION_THREAD_TIMEOUT("0514",
            "[ReplicateEventUseCase] --> invoke thread TimeoutException : __COMPLEMENT__");


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
