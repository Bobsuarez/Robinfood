package com.robinfood.paymentmethodsbc.dto.sso;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "SSOGenericResponseDTO")
@JsonInclude(Include.NON_NULL)
public class SSOGenericResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String status;

    private Integer code;

    private T result;

    private String messages;
}
