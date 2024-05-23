package com.robinfood.customersbc.thirdparties.domains;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDomain<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -4521776021408377910L;
    private Integer code;
    private String message;

    @JsonInclude(Include.NON_NULL)
    private T data;

    @JsonInclude(Include.NON_NULL)
    private ErrorDomain error;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(Include.NON_NULL)
    public static class ErrorDomain implements Serializable {
        @Serial
        private static final long serialVersionUID = -9006830613683132148L;
        private String type;
        private List<HashMap<String, String>> details;
    }
}
