package com.robinfood.core.dtos.orderSales;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrderActiveSalesDTO {

    private List<OrderSalesByCompanyDTO> companies;
}
