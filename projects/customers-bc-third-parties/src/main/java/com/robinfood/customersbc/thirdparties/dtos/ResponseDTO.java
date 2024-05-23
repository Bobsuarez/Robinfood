package com.robinfood.customersbc.thirdparties.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -4886549413899446187L;
    private Integer code;
    private String message;

    @JsonInclude(Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorDTO error;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(Include.NON_NULL)
    public static class ErrorDTO implements Serializable {
        @Serial
        private static final long serialVersionUID = 869422822403789560L;
        private String type;

        @JsonInclude(Include.NON_NULL)
        private List<Map<String, String>> details;
    }
}
