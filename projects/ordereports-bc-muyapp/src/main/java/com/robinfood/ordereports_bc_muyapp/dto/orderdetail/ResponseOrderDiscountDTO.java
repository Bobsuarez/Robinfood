package com.robinfood.ordereports_bc_muyapp.dto.orderdetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Setter
public class ResponseOrderDiscountDTO {

    private  Long productId;

    private  Long typeId;

    private  Double value;
}
