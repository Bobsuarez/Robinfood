package com.robinfood.dtos.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.utils.LogsUtil;
import com.robinfood.utils.ObjectMapperSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.robinfood.constants.Constants.CONTENT_TYPE;

public class BuilderResponseDTO {
    private static final Logger LOG = LogManager.getLogger(BuilderResponseDTO.class);

    private static final ObjectMapper objectMapper = ObjectMapperSingleton.getInstance();

    private int statusCode = 200;

    private Map<String, String> headers = Collections.emptyMap();

    private String rawBody;

    private Object objectBody;

    private byte[] binaryBody;

    private boolean base64Encoded;

    public BuilderResponseDTO() {
        this.headers = new HashMap<>();
        this.headers.put("Content-Type", CONTENT_TYPE);
    }

    public BuilderResponseDTO setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public BuilderResponseDTO setHeaders(Map<String, String> headers) {
        this.headers = headers;
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
        return new ApiGatewayResponseDTO(body, headers, base64Encoded, statusCode);
    }
}
