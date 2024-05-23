package com.robinfood.ordereports_bc_muyapp.dto.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseServiceDTO {

    private Double discount;

    private Long id;

    private String name;

    private Double subtotal;

    private Double tax;

    private Double total;

}
