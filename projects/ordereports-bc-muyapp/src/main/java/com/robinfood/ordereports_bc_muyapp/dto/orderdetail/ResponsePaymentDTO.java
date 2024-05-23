package com.robinfood.ordereports_bc_muyapp.dto.orderdetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ResponsePaymentDTO {

    private BigDecimal co2Total;

    private Double discount;

    private List<ResponsePaymentDiscountDTO> discounts;

    private Double invoice;

    private List<ResponsePaymentMethodDTO> methods;

    private Double subtotal;

    private Double tax;

    private Double total;

}
