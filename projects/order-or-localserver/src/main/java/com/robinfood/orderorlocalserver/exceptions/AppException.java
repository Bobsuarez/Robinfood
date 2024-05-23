package com.robinfood.orderorlocalserver.exceptions;

import jakarta.validation.constraints.NotNull;

public class AppException extends Exception {

    public AppException(@NotNull final Exception e) {
        super(e);
    }

}
