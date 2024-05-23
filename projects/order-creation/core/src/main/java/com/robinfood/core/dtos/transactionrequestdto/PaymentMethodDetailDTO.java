package com.robinfood.core.dtos.transactionrequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PaymentMethodDetailDTO implements Serializable {

    private String accountType;

    @JsonProperty("credit_card")
    private Long creditCard;

    private  String additionalInformation;

    private String approvalCode;

    private  String franchiseType;

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
