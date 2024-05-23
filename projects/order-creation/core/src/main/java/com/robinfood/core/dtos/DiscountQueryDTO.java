package com.robinfood.core.dtos;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class DiscountQueryDTO implements Serializable {

    private final Object criteriaInfo;

    private final List<DiscountContainerRequestDTO> discounts;
}
