package org.example.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HttpStatusConstants {

    SC_OK(200),
    SC_CREATED(201),
    SC_ACCEPTED(202),
    SC_BAD_REQUEST(400),
    SC_UNAUTHORIZED(401),
    SC_PAYMENT_REQUIRED(402),
    SC_FORBIDDEN(403),
    SC_NOT_FOUND(404),
    SC_CONFLICT(409),
    SC_PRECONDITION_FAILED(412),
    SC_INTERNAL_SERVER_ERROR(500),
    NOT_IMPLEMENTED(501),
    BAD_GATEWAY(502),
    SERVICE_UNAVAILABLE(503),
    GATEWAY_TIMEOUT(504);

    private final int codeHttp;
}
