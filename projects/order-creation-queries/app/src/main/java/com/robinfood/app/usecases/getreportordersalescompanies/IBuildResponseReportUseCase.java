package com.robinfood.app.usecases.getreportordersalescompanies;

import com.google.gson.JsonObject;
import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.dtos.orderSales.ResponseOrderActiveSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.ResponseOrderSalesDTO;

public interface IBuildResponseReportUseCase {

    ResponseOrderSalesDTO invoke (
            ResponseOrderActiveSalesDTO responseOrderactiveSalesDTO,
            JsonObject lambdaExchanges,
            CompaniesDTO listCompanies,
            String currentDate,
            String previousDate
    );
}
