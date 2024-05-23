package com.robinfood.dtos.response;

import lombok.Builder;

import java.util.Map;

@Builder
public class ApiGatewayResponseDTO extends BuilderResponseDTO {
    private final String body;

    private final Map<String, String> headers;

    private final boolean isBase64Encoded;

    private final int statusCode;

    public ApiGatewayResponseDTO(String body, Map<String, String> headers, boolean isBase64Encoded, int statusCode) {
        this.body = body;
        this.headers = headers;
        this.isBase64Encoded = isBase64Encoded;
        this.statusCode = statusCode;
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

    // API Gateway expects the property to be called "isBase64Encoded"
    public boolean isIsBase64Encoded() {
        return isBase64Encoded;
    }
}
