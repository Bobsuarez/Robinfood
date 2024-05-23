package com.robinfood.core.entities.paymentmethodpaidentities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodRefundEntity {

    private Long entityId;

    private Long entitySourceId;

    private String entitySourceReference;

    private String reason;
}