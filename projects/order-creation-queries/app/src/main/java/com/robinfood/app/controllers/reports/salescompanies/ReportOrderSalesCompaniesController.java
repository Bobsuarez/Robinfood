package com.robinfood.app.controllers.reports.salescompanies;

import com.robinfood.app.security.Permissions;
import com.robinfood.app.usecases.getreportordersalescompanies.IGetReportOrderSalesCompaniesUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.reportordersalescompanies.ResponseOrderSalesDTO;
import com.robinfood.core.enums.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.SALES_COMPANIES_ORDERS;
import static com.robinfood.core.constants.APIConstants.REPORTS_V1;

@RequestMapping(REPORTS_V1)
@RestController
@Slf4j
public class ReportOrderSalesCompaniesController implements IReportOrderSalesCompaniesController {

    private final IGetReportOrderSalesCompaniesUseCase getReportOrderSalesCompaniesUseCase;

    public ReportOrderSalesCompaniesController(
            IGetReportOrderSalesCompaniesUseCase getReportOrderSalesCompaniesUseCase
    ) {
        this.getReportOrderSalesCompaniesUseCase = getReportOrderSalesCompaniesUseCase;
    }

    @Override
    @GetMapping(SALES_COMPANIES_ORDERS)
    @PreAuthorize(Permissions.ORDER_REPORT_SALES)
    public ResponseEntity<ApiResponseDTO<ResponseOrderSalesDTO>> invoke(
            List<Integer> companies,
            LocalDateTime dateTimeCurrent
    ) {

        log.info("Receiving request get report order sales by companies");

        final Result<ResponseOrderSalesDTO> reportResponseDTOResult =
                getReportOrderSalesCompaniesUseCase.invoke(dateTimeCurrent, companies);

        ApiResponseDTO<ResponseOrderSalesDTO> apiResponseDTO;

        HttpStatus httpStatus;

        if (reportResponseDTOResult instanceof Result.Error) {
            httpStatus = ((Result.Error) reportResponseDTOResult).getHttpStatus();
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Error) reportResponseDTOResult).getException().getLocalizedMessage(),
                    httpStatus
            );
        } else {
            httpStatus = HttpStatus.OK;
            apiResponseDTO = new ApiResponseDTO<>(
                    ((Result.Success<ResponseOrderSalesDTO>) reportResponseDTOResult).getData(),
                    httpStatus
            );
        }
        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
