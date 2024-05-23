package com.robinfood.mocks.dtos.v1.request;

import com.robinfood.dtos.v1.APIGatewayProxyRequestEvent;

import java.util.HashMap;
import java.util.Map;

public class APIGatewayProxyRequestEventMock {

    public static APIGatewayProxyRequestEvent buildWithPathParameters(Map<String, String> pathParameters) {

        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHeaders(headersWithAuthorization());
        requestEvent.setPathParameters(pathParameters);
        return requestEvent;
    }

    public static APIGatewayProxyRequestEvent buildWithPathParametersWithBody(
            Map<String, String> pathParameters,
            String body
    ) {

        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHeaders(headersWithAuthorization());
        requestEvent.setPathParameters(pathParameters);
        requestEvent.setBody(body);
        return requestEvent;
    }

    public static APIGatewayProxyRequestEvent buildWithBody(String body) {

        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHeaders(headersWithAuthorization());
        requestEvent.setBody(body);
        return requestEvent;
    }

    public static APIGatewayProxyRequestEvent buildWithBodyAndPathParameters(Map<String, String> pathParameters, String body) {

        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHeaders(headersWithAuthorization());
        requestEvent.setPathParameters(pathParameters);
        requestEvent.setBody(body);
        return requestEvent;
    }

    public static APIGatewayProxyRequestEvent buildWithQueryParameters(Map<String, String> pathParameters) {

        APIGatewayProxyRequestEvent requestEvent = new APIGatewayProxyRequestEvent();
        requestEvent.setHeaders(headersWithAuthorization());
        requestEvent.setQueryStringParameters(pathParameters);
        return requestEvent;
    }

    private static Map<String, String> headersWithAuthorization() {
        Map<String, String> headers = new HashMap<>();
        headers.put("authorization", "Bearer some_jwt_token");
        return headers;
    }
}
