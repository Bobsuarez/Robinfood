package com.robinfood.paymentmethodsbc.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.enums.ResponseCode;
import com.robinfood.paymentmethodsbc.utils.JsonUtils;
import feign.FeignException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class PaymentMethodsException extends Exception {
    private static final long serialVersionUID = 1L;
    private final String message;
    private final int statusCode;

    public PaymentMethodsException(String message) {
        super();
        this.message = message;
        this.statusCode = ResponseCode.CLIENT_ERROR.getCode();
    }

    public PaymentMethodsException(String message, int statusCode) {
        super();
        this.message = message;
        this.statusCode = statusCode;
    }

    public PaymentMethodsException(FeignException feignException) {
        super();
        this.statusCode = feignException.status();
        this.message = getMessageFromResponse(feignException.contentUTF8());
    }

    private String getMessageFromResponse(String response) {
        try {
            final ResponseDTO<?> responseDTO = JsonUtils.jsonToObject(
                response,
                ResponseDTO.class
            );

            return responseDTO.getMessage();
        } catch (JsonProcessingException e) {
            log.warn(
                String.format("Error obteniendo cuerpo de excepción %s", response),
                e
            );
            return ResponseCode.CLIENT_ERROR.getMessage();
        }
    }
}
