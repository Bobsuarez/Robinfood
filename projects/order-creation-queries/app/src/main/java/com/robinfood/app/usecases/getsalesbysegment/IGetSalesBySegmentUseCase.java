package com.robinfood.app.usecases.getsalesbysegment;

import com.robinfood.core.dtos.report.salebysegment.response.SaleBySegmentResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IGetSalesBySegmentUseCase {

    SaleBySegmentResponseDTO invoke(
            List<Long> brandsList,
            List<Long> channelsList,
            LocalDateTime dateTimeCurrent,
            List<Long> paymentMethodsList,
            List<Long> storesList
    );

}
