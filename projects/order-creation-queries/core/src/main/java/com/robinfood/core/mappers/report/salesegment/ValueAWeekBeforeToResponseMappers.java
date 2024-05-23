package com.robinfood.core.mappers.report.salesegment;

import com.robinfood.core.dtos.report.salebysegment.response.SalesAWeekBeforeDTOResponse;

import java.math.BigDecimal;

public final class ValueAWeekBeforeToResponseMappers {

    private ValueAWeekBeforeToResponseMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static SalesAWeekBeforeDTOResponse valueAssignment(BigDecimal valueAWeekBefore) {

        return SalesAWeekBeforeDTOResponse.builder()
                .value(valueAWeekBefore)
                .build();
    }
}
