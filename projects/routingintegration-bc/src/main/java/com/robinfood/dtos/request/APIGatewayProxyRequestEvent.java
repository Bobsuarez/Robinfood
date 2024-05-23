package com.robinfood.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class APIGatewayProxyRequestEvent {

    private String body;
    private Map<String, String> headers;
    private Map<String, String> pathParameters;
    private Map<String, String> queryStringParameters;
}
