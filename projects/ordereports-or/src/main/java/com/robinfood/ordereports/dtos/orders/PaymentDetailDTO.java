package com.robinfood.ordereports.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentDetailDTO implements Serializable {

    private String authorizationCode;

    private CreditCardDTO creditCard;

    private Long generalPaymentMethodId;

    private Long id;

    private Long paymentGatewayId;

    private Long platformId;

    private PaymentStatusDTO status;

    private Long storeId;

    private TerminalDTO terminal;

    private BigDecimal total;

    private Long userId;

    private String uuid;
}
