package com.robinfood.paymentmethodsbc.enums;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum PlatformType {
    GATEWAY("pasarela"),
    ORCHESTRATOR("orquestador"),
    DATAPHONE("datafono"),
    CASH("efectivo"),
    INTEGRATIONS("integraciones"),
    FOODCOINS("foodcoins");

    private final String name;

    PlatformType(String name) {
        this.name = name;
    }

    public static PlatformType getPlatformByName(String name){
        return Arrays.stream(PlatformType.values())
            .filter(type -> type.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }
}
