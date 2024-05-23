package com.robinfood.app.usecases.getreportordersalescompanies;

import com.google.gson.JsonObject;
import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.dtos.orderSales.OrderSalesByCompanyDTO;
import com.robinfood.core.dtos.orderSales.ResponseOrderActiveSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.CompaniesOrderSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.ResponseOrderSalesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BuildResponseReportUseCase implements IBuildResponseReportUseCase{

    private final IBuildCompaniesOrderSalesUseCase buildCompaniesOrderSalesUseCase;

    public BuildResponseReportUseCase(
            IBuildCompaniesOrderSalesUseCase buildCompaniesOrderSalesUseCase
    ){
        this.buildCompaniesOrderSalesUseCase = buildCompaniesOrderSalesUseCase;
    }

    @Override
    public ResponseOrderSalesDTO invoke(
            ResponseOrderActiveSalesDTO responseOrderactiveSalesDTO,
            JsonObject lambdaExchanges,
            CompaniesDTO listCompanies,
            String currentDate,
            String previousDate) {

        ResponseOrderSalesDTO responseOrderSalesDTO = ResponseOrderSalesDTO.builder().build();

        List<CompaniesOrderSalesDTO>  listCompaniesOrderSalesDTO = new ArrayList<>();

        responseOrderactiveSalesDTO.getCompanies().forEach( (OrderSalesByCompanyDTO orderSalesCompany) ->{

            Optional<CompaniesOrderSalesDTO> companiesOrderSales = buildCompaniesOrderSalesUseCase.invoke(
                    lambdaExchanges,
                    orderSalesCompany,
                    listCompanies,
                    currentDate,
                    previousDate
            );

            companiesOrderSales.ifPresent(listCompaniesOrderSalesDTO::add);
        });

        responseOrderSalesDTO.setCompanies(listCompaniesOrderSalesDTO);

        return responseOrderSalesDTO;
    }
}
