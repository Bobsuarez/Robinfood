package com.robinfood.paymentmethodsbc.dto.jms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JmsTransactionStatusChangeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("transaction_id")
    private final Long transactionId;

    @JsonProperty("transaction_uuid")
    private final String transactionUuid;

    @JsonProperty("transaction_status")
    private final TransactionStatusChangeDTO transactionStatus;

    private final EntityTransactionStatusChangeDTO entity;

    private final PaymentDTO payment;

    @JsonProperty("payment_gateway")
    private final PaymentGatewayDTO paymentGateway;

    @JsonProperty("transaction_detail")
    private final TransactionDetailDTO transactionDetail;


    @Getter
    @Builder
    @AllArgsConstructor
    public static class TransactionDetailDTO
        implements Serializable {
        private static final long serialVersionUID = 1L;

        private final Long id;
        private final Integer countryId;
        private final Integer storeId;
        private final Integer userId;
        private final String userPhone;
        private final String creditCardMaskedNumber;
        private final String dataphoneReceiptNumber;
        private final String establishmentCode;
        private final String franchise;    
        private final Long generalPaymentMethodId;
        private final String generalPaymentMethodName;  
        private final Integer installments;
        private final Integer platformId;
        private final String platformName;
        private final String transactionDataphoneTerminalCode;
        private final String transactionDataphoneUniqueCode;
        private final String transactionTerminalName;
        private final String transactionTerminalUuid;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class EntityTransactionStatusChangeDTO
        implements Serializable {
        private static final long serialVersionUID = 1L;

        private final Long id;

        @JsonProperty("source_id")
        private final Long sourceId;

        private final String reference;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class TransactionStatusChangeDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        private final Long id;

        private final String name;

        private final String date;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PaymentDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        private final BigDecimal subtotal;

        private final BigDecimal tax;

        private final BigDecimal total;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PaymentGatewayDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        private final Long id;

        private final String name;
    }
}
