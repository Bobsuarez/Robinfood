package com.robinfood.app.controllers.reports.salesbysegment;

import com.robinfood.app.usecases.getsalesbysegment.IGetSalesBySegmentUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.report.salebysegment.DataIdsToFindTheSegment;
import com.robinfood.core.dtos.report.salebysegment.GetSaleBySegmentResponseDTO;
import com.robinfood.core.exceptions.AsyncOrderBcException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.REPORT_SALES_BY_SEGMENT;
import static com.robinfood.core.constants.APIConstants.REPORT_V1;

@RestController
@RequestMapping(REPORT_V1)
public class SalesBySegmentController implements ISalesBySegmentController {

    private final IGetSalesBySegmentUseCase getSalesBySegmentUseCase;

    public SalesBySegmentController(
            IGetSalesBySegmentUseCase getSalesBySegmentUseCase
    ) {
        this.getSalesBySegmentUseCase = getSalesBySegmentUseCase;
    }

    @Override
    @GetMapping(REPORT_SALES_BY_SEGMENT)
    public ResponseEntity<ApiResponseDTO<GetSaleBySegmentResponseDTO>> getSalesBySegment(
            List<Long> brandsList,
            List<Long> companiesList,
            List<Long> channelsList,
            LocalDateTime dateTimeCurrent,
            List<Long> paymentMethodsList,
            List<Long> storesList,
            List<String> timezones
    ) throws AsyncOrderBcException {

        final DataIdsToFindTheSegment toSearchRequestDTO = DataIdsToFindTheSegment.builder()
                .brandsList(brandsList)
                .companiesList(companiesList)
                .channelsList(channelsList)
                .dateToSearch(dateTimeCurrent)
                .paymentsMethodsList(paymentMethodsList)
                .storesList(storesList)
                .build();

        return ResponseEntity.ok(new ApiResponseDTO<>(getSalesBySegmentUseCase.invoke(timezones, toSearchRequestDTO)));
    }
}
