package com.robinfood.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIGatewayProxyRequestEventDTO {

    private String body;

    private Map<String, String> headers;

    private String httpMethod;

    private Boolean isBase64Encoded;

    private String path;

    private Map<String, String> pathParameters;

    private Map<String, String> queryStringParameters;

    private String resource;

    private Map<String, String> stageVariables;
}
