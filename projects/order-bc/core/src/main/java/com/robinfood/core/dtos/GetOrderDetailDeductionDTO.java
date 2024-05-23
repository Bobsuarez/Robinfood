package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetOrderDetailDeductionDTO {

    @JsonIgnore
    private final Long id;

    private final BigDecimal value;
}
