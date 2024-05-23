package com.robinfood.paymentmethodsbc.dto.api.transactions;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
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
@Schema(description = "RefundResponseDTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RefundResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private Long transactionId;

    @NotNull
    private StatusRefundDTO status;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Schema(description = "StatusRefundDTO")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class StatusRefundDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        private Long id;

        private String name;
    }
}
