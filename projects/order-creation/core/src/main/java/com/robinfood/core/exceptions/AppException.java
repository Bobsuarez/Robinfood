package com.robinfood.core.exceptions;

import javax.validation.constraints.NotNull;

public class AppException extends Exception {

    public AppException(@NotNull final Exception e) {
        super(e);
    }

}
