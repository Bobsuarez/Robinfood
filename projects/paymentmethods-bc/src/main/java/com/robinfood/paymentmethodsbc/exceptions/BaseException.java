package com.robinfood.paymentmethodsbc.exceptions;

import com.robinfood.paymentmethodsbc.enums.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Clase de Exepcion basica
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseException extends Exception {
    private static final long serialVersionUID = -6984282042317545209L;
    private ResponseCode code;

    public BaseException(ResponseCode code) {
        this.code = code;
    }

    public BaseException(ResponseCode code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String message) {
        super(message);
        this.code = ResponseCode.CLIENT_ERROR;
    }

    public BaseException(Throwable cause, ResponseCode code) {
        super(cause);
        this.code = code;
    }
}
