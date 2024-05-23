package com.robinfood.datamock;

import com.robinfood.dtos.request.APIGatewayProxyRequestEventDTO;
import com.robinfood.util.ObjectMapperSingleton;

import java.util.HashMap;
import java.util.Map;

public class APIGatewayProxyRequestEventDTOMock {

    public static APIGatewayProxyRequestEventDTO getDefault() {

        Map<String, String> header = new HashMap<>();
        header.put("authorization", "token");

        return APIGatewayProxyRequestEventDTO.builder()
                .headers(header)
                .body(ObjectMapperSingleton
                        .objectToJson(RequestRoutingIntegrationDTOMock.getDefault()))
                .build();
    }

    public static APIGatewayProxyRequestEventDTO getDefaultBodyNotComplete() {

        Map<String, String> header = new HashMap<>();
        header.put("authorization", "token");

        return APIGatewayProxyRequestEventDTO.builder()
                .headers(header)
                .body(ObjectMapperSingleton
                        .objectToJson(RequestRoutingIntegrationDTOMock.getDefaultNotComplete()))
                .build();
    }
}
