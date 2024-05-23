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
public class ServiceDTO implements Serializable {

    private Double discount;

    private Long id;

    private String name;

    private Double subtotal;

    private Double tax;

    private Double total;
}
