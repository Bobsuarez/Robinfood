package com.robinfood.core.mappers.report.salesegment;

import com.robinfood.core.dtos.report.salebysegment.response.SalesCurrentDTOResponse;

import java.math.BigDecimal;

public final class ValueCurrentToResponseMappers {

    private ValueCurrentToResponseMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static SalesCurrentDTOResponse valueAssignment(BigDecimal valueCurrent) {
        return SalesCurrentDTOResponse.builder()
                .value(valueCurrent)
                .build();
    }
}
