package com.robinfood.dtos.v1;

import lombok.Data;

import java.util.Map;

@Data
public class APIGatewayProxyRequestEvent {

    private String body;
    private Map<String, String> headers;
    private Map<String, String> pathParameters;
    private Map<String, String> queryStringParameters;
}
