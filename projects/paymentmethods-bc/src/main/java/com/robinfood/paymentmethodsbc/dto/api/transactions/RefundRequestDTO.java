package com.robinfood.paymentmethodsbc.dto.api.transactions;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Schema(description = "RefundRequestDTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RefundRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private Long transactionId;

    @NotEmpty
    private String reason;
}
