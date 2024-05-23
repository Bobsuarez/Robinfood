package com.robinfood.app.controllers.reports.salesstores;

import com.robinfood.app.security.Permissions;
import com.robinfood.app.usecases.getreportsalesstores.IGetReportSalesStoreGroupByPaymentMethodsUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.reportsalesstore.ReportSalesStoresDTO;
import com.robinfood.core.enums.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.robinfood.core.constants.APIConstants.SALES_STORE_PAYMENT_METHODS;
import static com.robinfood.core.constants.APIConstants.REPORTS_V1;

@RestController
@RequestMapping(REPORTS_V1)
@Slf4j
public class ReportSalesStoreGroupPaymentMethodsController implements IReportSalesStoreGroupPaymentMethodsController{

    private final IGetReportSalesStoreGroupByPaymentMethodsUseCase getReportSalesByStoreGroupByPaymentMethodsUseCase;

    public ReportSalesStoreGroupPaymentMethodsController (
            IGetReportSalesStoreGroupByPaymentMethodsUseCase getReportSalesByStoreGroupByPaymentMethodsUseCase
    ) {
        this.getReportSalesByStoreGroupByPaymentMethodsUseCase = getReportSalesByStoreGroupByPaymentMethodsUseCase;
    }

    @Override
    @GetMapping(SALES_STORE_PAYMENT_METHODS)
    @PreAuthorize(Permissions.ORDER_REPORT_SALES)
    public ResponseEntity<ApiResponseDTO<ReportSalesStoresDTO>> invoke (
            LocalDateTime dateTimeCurrent,
            Long storeId
    ) {

        log.info("Receiving request get sales stores {}, {}", dateTimeCurrent, storeId);

        Result<ReportSalesStoresDTO> resolutionResponseDTO = getReportSalesByStoreGroupByPaymentMethodsUseCase
                .invoke(dateTimeCurrent, storeId);

        ApiResponseDTO<ReportSalesStoresDTO> apiResponseDTO;
        HttpStatus httpStatus;

        if (resolutionResponseDTO instanceof Result.Error) {

            httpStatus = ((Result.Error) resolutionResponseDTO).getHttpStatus();

            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) resolutionResponseDTO).getException().getLocalizedMessage(),
                    httpStatus
            );
        } else {

            httpStatus = HttpStatus.OK;

            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<ReportSalesStoresDTO>) resolutionResponseDTO).getData(),
                    httpStatus
            );
        }

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
