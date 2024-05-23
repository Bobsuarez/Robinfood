package com.robinfood.ordereports_bc_muyapp.enums;

import com.robinfood.app.library.exception.template.IGenericMessage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderErrorLogEnum implements IGenericMessage {

    ERROR_NOT_FOUND(1000, "The user is not an employee, __COMPLEMENT__");


    private final int code;
    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
