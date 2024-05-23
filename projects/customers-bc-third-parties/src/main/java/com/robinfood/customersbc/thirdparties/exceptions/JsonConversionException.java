package com.robinfood.customersbc.thirdparties.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = false)
public class JsonConversionException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2679902926543727930L;
    private final String type;
    private final String message;

    public JsonConversionException(String type, String message) {
        super(message);
        this.type = type;
        this.message = message;
    }
}
