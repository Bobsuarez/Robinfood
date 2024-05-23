package com.robinfood.repository.report.salessegment;

import com.robinfood.core.dtos.report.salebysegment.GetSaleBySegmentDTO;
import com.robinfood.core.enums.Result;

import java.time.LocalDateTime;
import java.util.List;

public interface ISalesSegmentRepository {

    Result<GetSaleBySegmentDTO> getSalesSegment(
            List<Long> brands,
            List<Long> companies,
            List<Long> channels,
            LocalDateTime dateTimeCurrent,
            List<Long> paymentMethods,
            List<Long> stores,
            List<String> timezones,
            String token
    );

}
