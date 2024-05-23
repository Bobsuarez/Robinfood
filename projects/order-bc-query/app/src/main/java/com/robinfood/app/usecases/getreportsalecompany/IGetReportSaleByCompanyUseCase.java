package com.robinfood.app.usecases.getreportsalecompany;

import com.robinfood.core.dtos.report.sale.SaleReportResponseDTO;
import com.robinfood.core.exceptions.AsyncOrderBcException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Use case that returns the value of company sales
 */
public interface IGetReportSaleByCompanyUseCase {

    /**
     * retrieve the value sales according to the entered criteria
     *
     * @return list of sales of the companies
     */
    SaleReportResponseDTO invoke(
            List<Integer> companies,
            LocalDateTime dateTimeCurrent,
            List<String> timezones
    ) throws AsyncOrderBcException;
}
