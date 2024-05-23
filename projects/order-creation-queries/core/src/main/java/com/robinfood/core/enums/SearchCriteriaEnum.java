package com.robinfood.core.enums;

import lombok.Getter;

@Getter
public enum SearchCriteriaEnum {

    CLIENT_MOBILE("client_mobile", 3L),
    CLIENT_NAME("client_name", 2L),
    INTEGRATION_NUMBER("integration_number", 6L),
    INTERNAL_ID("internal_id", 5L),
    NUMBER_BILL("number_bill", 1L),
    ORDER_NUMBER("order_number", 4L);

    private final String criteriaName;
    private final Long id;

    SearchCriteriaEnum(String criteriaName, Long id) {
        this.criteriaName = criteriaName;
        this.id = id;
    }
}
