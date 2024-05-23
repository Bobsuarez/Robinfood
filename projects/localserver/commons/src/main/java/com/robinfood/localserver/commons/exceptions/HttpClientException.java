package com.robinfood.localserver.commons.exceptions;

public class HttpClientException extends Exception {

    private final transient Object error;

    public HttpClientException(String message) {
        super(message);
        error = null;
    }

    public HttpClientException(String message, Object error) {
        super(message);
        this.error = error;
    }

    public Object getError() {
        return error;
    }
}
