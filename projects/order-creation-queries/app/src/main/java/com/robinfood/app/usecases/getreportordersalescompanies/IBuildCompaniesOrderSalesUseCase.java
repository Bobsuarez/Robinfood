package com.robinfood.app.usecases.getreportordersalescompanies;

import com.google.gson.JsonObject;
import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.dtos.orderSales.OrderSalesByCompanyDTO;
import com.robinfood.core.dtos.reportordersalescompanies.CompaniesOrderSalesDTO;

import java.util.Optional;

public interface IBuildCompaniesOrderSalesUseCase {

    Optional<CompaniesOrderSalesDTO> invoke(
            JsonObject lambdaExchange,
            OrderSalesByCompanyDTO orderSalesCompany,
            CompaniesDTO listCompanies,
            String currentDate,
            String previousDate
    );
}
