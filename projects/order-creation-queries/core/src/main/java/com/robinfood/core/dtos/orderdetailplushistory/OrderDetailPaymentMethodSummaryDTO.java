package com.robinfood.core.dtos.orderdetailplushistory;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailPaymentMethodSummaryDTO {

    private final Long id;

    private final String name;

    private final Double value;
}
