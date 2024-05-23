package com.robinfood.app.controllers.reports.salesbysegment;

import com.robinfood.app.security.Permissions;
import com.robinfood.app.usecases.getsalesbysegment.GetSalesBySegmentUseCase;
import com.robinfood.app.usecases.getsalesbysegment.IGetSalesBySegmentUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.report.salebysegment.response.SaleBySegmentResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.REPORT_SALES_BY_SEGMENT;
import static com.robinfood.core.constants.APIConstants.REPORT_V1;

@RestController
@RequestMapping(REPORT_V1)
@Slf4j
public class SalesBySegmentController implements ISalesBySegmentController {

    private final IGetSalesBySegmentUseCase getSalesBySegmentUseCase;

    public SalesBySegmentController(GetSalesBySegmentUseCase getSalesBySegmentUseCase) {
        this.getSalesBySegmentUseCase = getSalesBySegmentUseCase;
    }

    @Override
    @GetMapping(REPORT_SALES_BY_SEGMENT)
    @PreAuthorize(Permissions.ORDER_REPORT_SALES)
    public ResponseEntity<ApiResponseDTO<SaleBySegmentResponseDTO>> invoke(
            List<Long> brands,
            List<Long> channels,
            LocalDateTime dateTimeCurrent,
            List<Long> paymentMethods,
            List<Long> stores
    ) {

        log.info("Receiving Request Sales By Segment brands: {}, channels: {}, dateTimeCurrent: {}, " +
                "paymentMethods: {}, stores: {}", brands, channels, dateTimeCurrent, paymentMethods, stores);

        return ResponseEntity.ok(new ApiResponseDTO<>(
                getSalesBySegmentUseCase.invoke(
                        brands,
                        channels,
                        dateTimeCurrent,
                        paymentMethods,
                        stores
                ))
        );
    }
}
