package com.robinfood.customersbc.thirdparties.enums;

import lombok.Getter;

@Getter
public enum GrantedAuthorityEnum {
    SERVICE("service"),
    PUBLIC("public"),
    INTERNAL("internal"),
    USER_ID("user_id"),
    USER("user"),
    MOD("mod"),
    PER("per"),
    ROLE("ROLE_");

    private final String name;

    GrantedAuthorityEnum(String name) {
        this.name = name;
    }

}
