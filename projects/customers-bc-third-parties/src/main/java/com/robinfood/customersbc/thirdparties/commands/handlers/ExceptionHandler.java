package com.robinfood.customersbc.thirdparties.commands.handlers;

import com.robinfood.customersbc.thirdparties.dtos.ResponseDTO;

public interface ExceptionHandler {

    ResponseDTO<Object> handleException(Throwable exception);
}
