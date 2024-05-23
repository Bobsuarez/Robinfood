package com.robinfood.core.mappers.report.salesegment;

import com.robinfood.core.dtos.report.salebysegment.response.PercentageSalesDifferenceDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.SalesAWeekBeforeDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.SalesCurrentDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.TotalDTOResponse;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_NAME_TOTAL;

public final class TotalDTOResponseMappers {

    private TotalDTOResponseMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static TotalDTOResponse totalToResponse(
            String currencyType,
            PercentageSalesDifferenceDTOResponse percentageSalesDifferenceDTOResponse,
            SalesAWeekBeforeDTOResponse salesAWeekBeforeDTOResponse,
            SalesCurrentDTOResponse salesCurrentDTOResponse
    ) {

        return TotalDTOResponse.builder()
                .name(DEFAULT_NAME_TOTAL)
                .currency(currencyType)
                .salesCurrent(salesCurrentDTOResponse)
                .salesAWeekBefore(salesAWeekBeforeDTOResponse)
                .percentageSalesDifference(percentageSalesDifferenceDTOResponse)
                .build();
    }
}
