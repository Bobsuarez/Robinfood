package com.robinfood.ordereports.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentMethodDTO implements Serializable {

    private Long id;

    private String name;

    private Double value;
}
