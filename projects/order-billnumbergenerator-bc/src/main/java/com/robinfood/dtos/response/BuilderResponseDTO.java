package com.robinfood.dtos.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.robinfood.constants.GeneralConstants.APPLICATION_JSON;
import static com.robinfood.constants.GeneralConstants.CONTENT_TYPE;

public class BuilderResponseDTO {

    private static final ObjectMapper objectMapper = ObjectMapperSingleton.getInstance();

    private int statusCode = HttpStatusConstants.SC_OK.getCodeHttp();
    private Map<String, String> headers = Collections.emptyMap();
    private String rawBody;
    private Object objectBody;
    private byte[] binaryBody;
    private boolean base64Encoded;

    public BuilderResponseDTO() {
        this.headers = new HashMap<>();
        this.headers.put(CONTENT_TYPE, APPLICATION_JSON);
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
     * @param base64Encoded the base64 encoded response
     * @return the response object that will be returned
     */
    public BuilderResponseDTO setBase64Encoded(boolean base64Encoded) {
        this.base64Encoded = base64Encoded;
        return this;
    }

    public ApiGatewayResponseDTO build() {
        String body = null;
        if (rawBody != null) {
            body = rawBody;
        } else if (objectBody != null) {
            try {
                body = objectMapper.writeValueAsString(objectBody);
            } catch (JsonProcessingException e) {
                LogsUtil.error("failed to serialize object", e);
                throw new RuntimeException(e);
            }
        } else if (binaryBody != null) {
            body = new String(Base64.getEncoder().encode(binaryBody), StandardCharsets.UTF_8);
        }
        return new ApiGatewayResponseDTO(statusCode, body, headers, base64Encoded);
    }
}