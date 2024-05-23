package com.robinfood.core.dtos.orderbyuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseServiceDTO {

    private Double discount;

    private Long id;

    private String name;

    private Double subtotal;

    private Double tax;

    private Double total;

}
