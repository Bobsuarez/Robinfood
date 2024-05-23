package com.robinfood.core.mappers.report.salesegment;

import com.robinfood.core.dtos.report.salebysegment.response.PercentageSalesDifferenceDTOResponse;

import java.math.BigDecimal;

public final class PercentageToResponseMappers {

    private PercentageToResponseMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static PercentageSalesDifferenceDTOResponse valueAssignment(BigDecimal valueCurrent) {
        return PercentageSalesDifferenceDTOResponse.builder()
                .value(valueCurrent)
                .build();
    }
}
