package com.robinfood.localserver.commons.exceptions;

import feign.FeignException;

public class NotFoundFeignException extends FeignException {
    public NotFoundFeignException(int status, String message) {
        super(status, message);
    }
}
