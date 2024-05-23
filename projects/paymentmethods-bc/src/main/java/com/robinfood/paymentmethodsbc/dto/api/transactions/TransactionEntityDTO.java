package com.robinfood.paymentmethodsbc.dto.api.transactions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntityDTO {

    private String uuid;

    private Long id;

    private LocalDateTime createdAt;

    private String transactionReference;

    private  String transactionCode;

    private boolean succeeded;

    private String authorizationCode;

    private  StatusDTO status;

    private  EntityDTO entity;

    private PaymentDTO payment;

    private PaymentMethodsDTO paymentMethod;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StatusDTO {

        private Long id;

        private String name;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EntityDTO {

        private Long id;

        private Long sourceId;

        private String reference;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentDTO {


        private BigDecimal subtotal;

        private BigDecimal total;

        private  BigDecimal tax;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentMethodsDTO {

        private Long id;

        private String name;

    }


}
