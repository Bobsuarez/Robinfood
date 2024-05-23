package com.robinfood.core.logging.constants;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;

@Getter
public enum OriginConsumerEnum {

    RF_APP(2, "RFApp"),
    SELF_MANAGEMENT(4, "Autogestión"),
    UBER_EATS(6, "Ubereats"),
    RAPPI(7, "Rappi"),
    IFOOD(8, "iFood"),
    POS(10, "POS"),
    DIDI(11, "DiDi"),
    CARDAPIODIGITAL(21, "Cardapio Digital"),
    TUORDEN(22, "Tu orden");

    private final int id;
    private final String name;

    OriginConsumerEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String parseName(final Number idOrigin) {

        final Optional<OriginConsumerEnum> findOrigins = Arrays.stream(values())
                .filter(value -> value.getId() == idOrigin.intValue())
                .findFirst();

        if (findOrigins.isPresent()) {
            return findOrigins.get().getName();
        }

        return String.format("Without origin %s", DEFAULT_STRING_VALUE);
    }
}
