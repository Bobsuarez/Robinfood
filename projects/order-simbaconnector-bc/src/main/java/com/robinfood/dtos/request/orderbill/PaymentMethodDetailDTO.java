package com.robinfood.dtos.request.orderbill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PaymentMethodDetailDTO {

    private String accountType;

    private String additionalInformation;

    private String approvalCode;

    @JsonProperty("credit_card")
    private Long creditCard;

    private String franchiseType;

    @JsonProperty("installments_number")
    private Long installmentsNumber;

    private Long orderId;

    private Long orderPaymentId;

    @JsonProperty("payment_getaway_id")
    private Long paymentGetawayId;

    private String posTerminalCode;

    private Long providerId;

    private String selfManagementCode;

    private Long uniqueTransactionId;

}
