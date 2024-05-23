package com.robinfood.paymentmethodsbc.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Clase de Exepcion basica
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DecryptException extends RuntimeException {
    private String message;

    public DecryptException(String message) {
        super(message);
    }
    public DecryptException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }
}
