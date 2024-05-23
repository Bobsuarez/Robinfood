package com.robinfood.dtos.v1.response;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.robinfood.util.ObjectMapperSingletonUtil.objectToJson;

@Slf4j
public class BuilderResponseDTO {

    private boolean base64Encoded;
    private byte[] binaryBody;
    private Map<String, String> headers;
    private String rawBody;
    private Object objectBody;
    private int statusCode = HttpStatus.SC_OK;

    public BuilderResponseDTO() {
        this.headers = new HashMap<>();
        this.headers.put("Content-Type", "application/json");
    }

    public BuilderResponseDTO setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public BuilderResponseDTO setHeaders(Map<String, String> header) {
        this.headers = header;
        return this;
    }

    /**
     * Builds the {@link ApiGatewayResponseDTO} using the passed raw body string.
     */
    public BuilderResponseDTO setRawBody(String rawBody) {
        this.rawBody = rawBody;
        return this;
    }

    /**
     * Builds the {@link ApiGatewayResponseDTO} using the passed object body
     * converted to JSON.
     */
    public BuilderResponseDTO setObjectBody(Object objectBody) {
        this.objectBody = objectBody;
        return this;
    }

    /**
     * Builds the {@link ApiGatewayResponseDTO} using the passed binary body
     * encoded as base64. {@link #setBase64Encoded(boolean)
     * setBase64Encoded(true)} will be in invoked automatically.
     */
    public BuilderResponseDTO setBinaryBody(byte[] binaryBody) {
        this.binaryBody = binaryBody;
        setBase64Encoded(true);
        return this;
    }

    /**
     * A binary or rather a base64encoded responses requires
     * <ol>
     * <li>"Binary Media Types" to be configured in API Gateway
     * <li>a request with an "Accept" header set to one of the "Binary Media
     * Types"
     * </ol>
     */
    public BuilderResponseDTO setBase64Encoded(boolean base64Encoded) {
        this.base64Encoded = base64Encoded;
        return this;
    }

    public ApiGatewayResponseDTO build() {

        String body = null;
        if (Objects.nonNull(rawBody)) {
            body = rawBody;
        } else if (Objects.nonNull(objectBody)) {
            body = objectToJson(objectBody);
        } else if (Objects.nonNull(binaryBody)) {
            body = new String(Base64.getEncoder().encode(binaryBody), StandardCharsets.UTF_8);
        }

        return new ApiGatewayResponseDTO(body, headers, base64Encoded, statusCode);
    }
}