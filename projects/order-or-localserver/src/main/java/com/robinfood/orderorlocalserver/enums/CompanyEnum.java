package com.robinfood.orderorlocalserver.enums;

import lombok.Getter;
import java.util.Arrays;

@Getter
public enum CompanyEnum {

    ROBIN_FOOD_COLOMBIA(1, "Colombia"),
    ROBIN_FOOD_BRAZIL(5, "Brazil");
    private final int id;
    private final String name;

    CompanyEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String parseName(final Number idCompany) {
        return Arrays.stream(values()).filter(value -> value.getId() == idCompany.intValue())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Company id not found"))
                .getName();
    }
}
