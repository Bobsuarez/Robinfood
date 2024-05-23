package com.robinfood.paymentmethodsbc.dto.api.transactions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Schema(description = "PaymentResponseDTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentInitResponseDTO implements Serializable {
    private static final long serialVersionUID = -7724636895209191750L;
    private boolean generated;
    private Long id;
    private String uuid;
    private String authorizarionCode;
    private StatusDTO status;
    private Long platformTypeId;

    @JsonProperty("platformTypeInfo")
    private PaymentGatewayTransactionDTO paymentGateway;

    @JsonProperty("entity")
    private EntityDTO entityTransactionDTO;

    @JsonProperty("payment")
    private PaymentDTO paymentTransactionDTO;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class StatusDTO implements Serializable {
        private static final long serialVersionUID = 5894787116107197042L;
        private Long id;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PaymentGatewayTransactionDTO implements Serializable {
        private static final long serialVersionUID = -4697017721207890188L;
        private Long paymentGatewayId;
        private String paymentGatewayName;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class EntityDTO implements Serializable {
        private static final long serialVersionUID = 1762616431607618127L;
        private Long id;
        private Long source;
        private String reference;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PaymentDTO implements Serializable {
        private static final long serialVersionUID = -1251389626723470844L;
        private BigDecimal subtotal;
        private BigDecimal tax;
        private BigDecimal total;
    }
}
