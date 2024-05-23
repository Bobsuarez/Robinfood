package com.robinfood.exceptions;

import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.response.ResponseDTO;

public class ApplicationException extends RuntimeException {

    protected final ResponseDTO responseDTO;

    protected final ApiGatewayResponseDTO apiGatewayResponseDTO;

    public ApplicationException(ResponseDTO responseMapper, String inMessage) {
        super(inMessage);
        this.responseDTO = responseMapper;
        this.apiGatewayResponseDTO = ApiGatewayResponseDTO.builder()
                .setStatusCode(responseMapper.getCode())
                .setObjectBody(responseMapper)
                .build();
    }

    public ApiGatewayResponseDTO getApiGatewayResponseDTO() {
        return apiGatewayResponseDTO;
    }
}
