package com.robinfood.core.dtos.paymentmethodpaiddto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PaymentMethodPaidReferenceDTO implements Serializable {

    private Long id;

    private String reference;

    private Integer source;
}
