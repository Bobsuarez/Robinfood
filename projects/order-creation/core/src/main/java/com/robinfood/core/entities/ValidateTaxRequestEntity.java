package com.robinfood.core.entities;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_LONG_VALUE;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class ValidateTaxRequestEntity {

    private final Map<String, Object> criteria;

    private final List<ValidateTaxItemRequestEntity> items;

    private final Long orderId = DEFAULT_LONG_VALUE;
}
