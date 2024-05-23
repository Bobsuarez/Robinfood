package com.robinfood.app.usecases.getreportordersalescompanies;

import com.robinfood.core.dtos.reportordersalescompanies.ResponseOrderSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.TotalCompaniesOrderSalesDTO;

public interface IBuildTotalCompaniesOrderSalesUseCase {

    TotalCompaniesOrderSalesDTO invoke (
            ResponseOrderSalesDTO responseOrderSalesDTO
    );
}
