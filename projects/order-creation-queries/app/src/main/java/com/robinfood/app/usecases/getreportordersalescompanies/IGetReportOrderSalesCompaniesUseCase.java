package com.robinfood.app.usecases.getreportordersalescompanies;

import com.robinfood.core.dtos.reportordersalescompanies.ResponseOrderSalesDTO;
import com.robinfood.core.enums.Result;

import java.time.LocalDateTime;
import java.util.List;

public interface IGetReportOrderSalesCompaniesUseCase {

    /**
     * get list of order daily sales
     * @param dateTimeCurrent date to consult information
     * @param idsCompanies ids companies
     * @return list of order sales by companies
     */
    Result<ResponseOrderSalesDTO> invoke(LocalDateTime dateTimeCurrent, List<Integer> idsCompanies);
}
