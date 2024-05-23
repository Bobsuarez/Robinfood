package com.robinfood.exceptions;

import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.response.ResponseDTO;

public class ApplicationException extends RuntimeException {

    protected ResponseDTO responseDTO;

    protected ApiGatewayResponseDTO apiGatewayResponseDTO;

    public ApplicationException(ResponseDTO responseMapper, String inMessage) {
        super(inMessage);
        this.responseDTO = responseMapper;
        this.apiGatewayResponseDTO = ApiGatewayResponseDTO.builder()
                .setStatusCode(responseMapper.getCode())
                .setObjectBody(responseMapper)
                .build();
    }

    public ApplicationException(ApplicationException applicationException) {
        super(applicationException.getMessage());
        this.responseDTO = applicationException.getResponseDTO();
        this.apiGatewayResponseDTO = applicationException.getApiGatewayResponseDTO();
    }

    public ApiGatewayResponseDTO getApiGatewayResponseDTO() {
        return apiGatewayResponseDTO;
    }

    public ResponseDTO getResponseDTO() {
        return responseDTO;
    }

}
