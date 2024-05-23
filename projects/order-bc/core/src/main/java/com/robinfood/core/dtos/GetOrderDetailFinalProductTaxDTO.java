package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetOrderDetailFinalProductTaxDTO {

    private final Long id;

    @JsonIgnore
    private final Long taxId;

    private final Long taxTypeId;

    private final String taxTypeName;

    private final Double value;

    private final Long familyTypeId;

    private final Double price;

    @JsonIgnore
    private final Long orderFinalProductId;
}
