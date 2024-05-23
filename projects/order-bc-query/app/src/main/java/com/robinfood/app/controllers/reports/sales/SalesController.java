package com.robinfood.app.controllers.reports.sales;

import com.robinfood.app.usecases.getreportsalecompany.IGetReportSaleByCompanyUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.report.sale.SaleReportResponseDTO;
import com.robinfood.core.exceptions.AsyncOrderBcException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static com.robinfood.core.constants.APIConstants.REPORT_SALES_BY_IDS_COMPANY_LIST;
import static com.robinfood.core.constants.APIConstants.REPORT_V1;

@RestController
@RequestMapping(REPORT_V1)
@Slf4j
public class SalesController implements ISalesController {

    private final IGetReportSaleByCompanyUseCase getReportSaleByCompanyUseCase;

    public SalesController(IGetReportSaleByCompanyUseCase getReportSaleByCompanyUseCase) {
        this.getReportSaleByCompanyUseCase = getReportSaleByCompanyUseCase;
    }

    @Override
    @GetMapping(REPORT_SALES_BY_IDS_COMPANY_LIST)
    public ResponseEntity<ApiResponseDTO<SaleReportResponseDTO>> invoke(
            List<Integer> companies,
            LocalDateTime dateTimeCurrent,
            List<String> timezones
    ) throws AsyncOrderBcException {

        return ResponseEntity.ok(new ApiResponseDTO<>(
                getReportSaleByCompanyUseCase.invoke(companies, dateTimeCurrent, timezones))
        );
    }
}
