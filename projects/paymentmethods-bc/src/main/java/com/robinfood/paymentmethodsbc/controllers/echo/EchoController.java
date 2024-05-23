package com.robinfood.paymentmethodsbc.controllers.echo;

import com.robinfood.paymentmethodsbc.annotations.BaseResponse;
import com.robinfood.paymentmethodsbc.controllers.echo.docs.EchoDocs;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.enums.ResponseCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@BaseResponse
@RequestMapping("/echo")
public class EchoController implements EchoDocs {

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping
    public ResponseDTO<String> echo(String message) {
        return new ResponseDTO<>(
            ResponseCode.SUCCESS.getCode(),
            "Su mensaje fue devuelto",
            message,
            new ResponseDTO.ErrorDTO()
        );
    }
}
