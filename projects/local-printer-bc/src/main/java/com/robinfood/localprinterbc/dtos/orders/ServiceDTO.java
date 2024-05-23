package com.robinfood.localprinterbc.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ServiceDTO {
    private Long id;

    private String name;

    private Long quantity;

    private Double unitPrice;

    private String priceNt;// precio neto
    private String discount;//descuento
    private String taxPrice;// impuesto
    private Double taxPercentage;// valor del porcentaje del descuento
    private String total;
}
