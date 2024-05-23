package com.robinfood.dtos.response;

import java.util.Map;

public class ApiGatewayResponseDTO extends BuilderResponseDTO {

    private final int statusCode;
    private final String body;
    private final Map<String, String> headers;
    private final boolean isBase64Encoded;


    public ApiGatewayResponseDTO(int statusCode, String body, Map<String, String> headers, boolean isBase64Encoded) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = headers;
        this.isBase64Encoded = isBase64Encoded;
    }

    public static BuilderResponseDTO builder() {
        return new BuilderResponseDTO();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    // API Gateway expects the property to be called "isBase64Encoded" => isIs
    public boolean isIsBase64Encoded() {
        return isBase64Encoded;
    }

}
