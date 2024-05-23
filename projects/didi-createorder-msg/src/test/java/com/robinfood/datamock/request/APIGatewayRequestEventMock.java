package com.robinfood.datamock.request;

import com.robinfood.constants.GeneralConstants;
import com.robinfood.datamock.OrderToCreateDTOMock;
import com.robinfood.util.ObjectMapperSingletonUtil;

import java.util.HashMap;
import java.util.Map;

public class APIGatewayRequestEventMock {

    public static Map<String, Object> build() {

        Map<String, Object> objectReturn = new HashMap<>();

        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("id", "1");
        pathParameters.put("code", "CHANGE_STATUS");

        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("uuid", "1rfge433-6697-4050-8b8c-64cbb12");

        objectReturn.put("headers", getHeaders());
        objectReturn.put("pathParameters", pathParameters);
        objectReturn.put("queryStringParameters", queryParameters);

        return objectReturn;
    }

    public static Map<String, Object> buildWithBody(String body) {

        Map<String, Object> objectReturn = new HashMap<>();

        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("id", "1");
        pathParameters.put("code", "CHANGE_STATUS");

        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("uuid", "1rfge433-6697-4050-8b8c-64cbb12");

        objectReturn.put("headers", getHeaders());
        objectReturn.put("pathParameters", pathParameters);
        objectReturn.put("queryStringParameters", queryParameters);
        objectReturn.put("body", body);

        return objectReturn;
    }

    public static String getBody() {
        return ObjectMapperSingletonUtil.objectToJson(OrderToCreateDTOMock.getDataDefault());
    }

    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9" +
                ".eyJuYW1lIjoib3JkZXItY3JlYXRpb24iLCJhdWQiOiJzZXJ2aWNlIiwic3ViIjoib3JkZXItY3JlYXRpb24udjEiLCJwZXIiOltdLCJqdGkiOiI3MTY2Yzc3My05MGU2LTRiNWItODZkOC03MmQ4NTM2MTgwMWEiLCJpc3MiOiJiYWNrb2ZmaWNlLnYxIiwiaWF0IjoxNjg1NzQwMDcwLCJleHAiOjE2ODU3NDA2NzB9._ry5JWpRkaYzWwJcwZt2je5gl0u4Wa6LwYLfFEShT8ytwzgJLELE0TKskrWqpaBA0ZNZoFHcpPR3UWdG6MDDnA");

        headers.put(GeneralConstants.MESSAGE_FROM_HEADER, "from");
        headers.put(GeneralConstants.MESSAGE_COUNTRY_HEADER, "country");
        return headers;
    }

    public static Map<String, String> getHeadersOut() {
        Map<String, String> headers = new HashMap<>();
        headers.put("authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9" +
                ".eyJuYW1lIjoib3JkZXItY3JlYXRpb24iLCJhdWQiOiJzZXJ2aWNlIiwic3ViIjoib3JkZXItY3JlYXRpb24udjEiLCJwZXIiOltdLCJqdGkiOiI3MTY2Yzc3My05MGU2LTRiNWItODZkOC03MmQ4NTM2MTgwMWEiLCJpc3MiOiJiYWNrb2ZmaWNlLnYxIiwiaWF0IjoxNjg1NzQwMDcwLCJleHAiOjE2ODU3NDA2NzB9._ry5JWpRkaYzWwJcwZt2je5gl0u4Wa6LwYLfFEShT8ytwzgJLELE0TKskrWqpaBA0ZNZoFHcpPR3UWdG6MDDnA");

        headers.put(GeneralConstants.MESSAGE_FROM_OUT, "from");
        headers.put(GeneralConstants.MESSAGE_COUNTRY_OUT, "country");
        return headers;
    }
}
