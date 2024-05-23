package com.robinfood.core.dtos.orderbyuser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ResponseFinalProductDTO {

    private Long brandId;

    private BigDecimal co2Total;

    private List<ResponseFinalProductGroupDTO> groups;

    private Long id;

    private String image;

    private String name;

    private Double price;

    private Integer quantity;

    private Double value;

}
