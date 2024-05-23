package org.example.exceptions;

import org.example.dtos.response.ApiGatewayResponseDTO;
import org.example.dtos.response.ResponseDTO;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("OverridableMethodCallInConstructor")
public class ApplicationException extends RuntimeException {

    protected ResponseDTO responseDTO;
    protected ApiGatewayResponseDTO apiGatewayResponseDTO;

    protected Map<String, String> headerResponse;

    public ApplicationException(ResponseDTO responseMapper, String inMessage) {
        super(inMessage);
        this.responseDTO = responseMapper;
        this.apiGatewayResponseDTO = ApiGatewayResponseDTO.builder()
                .setStatusCode(responseMapper.getCode())
                .setObjectBody(responseMapper)
                .build();
    }

    public Map<String, String> getHeaderResponse() {
        this.headerResponse = new HashMap<>();
        this.headerResponse.put("Content-Type", "application/json");
        return headerResponse;
    }

    public ApiGatewayResponseDTO getApiGatewayResponseDTO() {
        return apiGatewayResponseDTO;
    }

}
