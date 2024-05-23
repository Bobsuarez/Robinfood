package com.robinfood.datamock;

import java.util.HashMap;
import java.util.Map;

public class APIGatewayRequestDTOMock {

    public static Map<String, Object> getDefault() {

        Map<String, Object> objectReturn = new HashMap<>();

        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("id", "1");
        pathParameters.put("code", "CHANGE_STATUS");

        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("uuid", "1rfge433-6697-4050-8b8c-64cbb12");

        Map<String, String> headers = new HashMap<>();
        headers.put("authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9" +
                ".eyJuYW1lIjoib3JkZXItY3JlYXRpb24iLCJhdWQiOiJzZXJ2aWNlIiwic3ViIjoib3JkZXItY3JlYXRpb24udjEiLCJwZXIiOltdLCJqdGkiOiI3MTY2Yzc3My05MGU2LTRiNWItODZkOC03MmQ4NTM2MTgwMWEiLCJpc3MiOiJiYWNrb2ZmaWNlLnYxIiwiaWF0IjoxNjg1NzQwMDcwLCJleHAiOjE2ODU3NDA2NzB9._ry5JWpRkaYzWwJcwZt2je5gl0u4Wa6LwYLfFEShT8ytwzgJLELE0TKskrWqpaBA0ZNZoFHcpPR3UWdG6MDDnA");

        objectReturn.put("headers", pathParameters);
        objectReturn.put("pathParameters", headers);
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

        Map<String, String> headers = new HashMap<>();
        headers.put("authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9" +
                ".eyJuYW1lIjoib3JkZXItY3JlYXRpb24iLCJhdWQiOiJzZXJ2aWNlIiwic3ViIjoib3JkZXItY3JlYXRpb24udjEiLCJwZXIiOltdLCJqdGkiOiI3MTY2Yzc3My05MGU2LTRiNWItODZkOC03MmQ4NTM2MTgwMWEiLCJpc3MiOiJiYWNrb2ZmaWNlLnYxIiwiaWF0IjoxNjg1NzQwMDcwLCJleHAiOjE2ODU3NDA2NzB9._ry5JWpRkaYzWwJcwZt2je5gl0u4Wa6LwYLfFEShT8ytwzgJLELE0TKskrWqpaBA0ZNZoFHcpPR3UWdG6MDDnA");

        objectReturn.put("headers", pathParameters);
        objectReturn.put("pathParameters", headers);
        objectReturn.put("queryStringParameters", queryParameters);
        objectReturn.put("body", body);

        return objectReturn;
    }
}
