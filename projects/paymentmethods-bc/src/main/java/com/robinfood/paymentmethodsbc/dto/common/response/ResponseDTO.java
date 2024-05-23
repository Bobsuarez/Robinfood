package com.robinfood.paymentmethodsbc.dto.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "ResponseDTO")
public class ResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = 1395585147770685271L;

    private Integer code;

    private String message;

    @JsonInclude(Include.NON_NULL)
    private T data;

    @JsonInclude(Include.NON_NULL)
    private ErrorDTO error;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "ErrorDTO")
    @JsonInclude(Include.NON_NULL)
    public static class ErrorDTO implements Serializable {
        private static final long serialVersionUID = -5107932231792797811L;

        private String type;

        @JsonProperty("details")
        private List<HashMap<String, String>> details;
    }
}
