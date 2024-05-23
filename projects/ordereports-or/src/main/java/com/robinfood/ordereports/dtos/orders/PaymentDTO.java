package com.robinfood.ordereports.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentDTO implements Serializable {

    private Double co2Total;

    private PaymentDetailDTO detail;

    private List<PaymentMethodDTO> methods;

    private Double subtotal;

    private Double tax;

    private Double total;
}
