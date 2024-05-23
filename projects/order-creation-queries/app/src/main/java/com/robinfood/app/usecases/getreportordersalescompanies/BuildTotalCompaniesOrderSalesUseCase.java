package com.robinfood.app.usecases.getreportordersalescompanies;

import com.robinfood.core.dtos.reportordersalescompanies.CompaniesOrderSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.ResponseOrderSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.TotalCompaniesOrderSalesDTO;
import com.robinfood.core.mappers.BuildCompaniesOrderSalesMappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.robinfood.core.constants.GlobalConstants.CURRENCY_USD;
import static com.robinfood.core.constants.GlobalConstants.NAME_TOTAL_COMPANY;

@Service
@Slf4j
public class BuildTotalCompaniesOrderSalesUseCase implements IBuildTotalCompaniesOrderSalesUseCase{

    @Override
    public TotalCompaniesOrderSalesDTO invoke(ResponseOrderSalesDTO responseOrderSalesDTO) {

        TotalCompaniesOrderSalesDTO totalCompaniesOrderSalesDTO = TotalCompaniesOrderSalesDTO.builder().build();

        totalCompaniesOrderSalesDTO.setName(NAME_TOTAL_COMPANY);
        totalCompaniesOrderSalesDTO.setCurrency(CURRENCY_USD);

        BigDecimal newSalesAWeekBeforeUsd = BigDecimal.ZERO;
        BigDecimal newSalesCurrentUsd = BigDecimal.ZERO;

        BigDecimal salesAWeekBefore = BigDecimal.ZERO;
        BigDecimal salesCurrent = BigDecimal.ZERO;

        for (CompaniesOrderSalesDTO companiesOrderSalesDTO : responseOrderSalesDTO.getCompanies()) {

            newSalesAWeekBeforeUsd = newSalesAWeekBeforeUsd.add(
                    companiesOrderSalesDTO
                            .getOrders()
                            .getSalesAWeekBefore()
                            .getCurrency()
                            .getUsd()
            );

            newSalesCurrentUsd = newSalesCurrentUsd.add(
                    companiesOrderSalesDTO
                            .getOrders()
                            .getSalesCurrent()
                            .getCurrency()
                            .getUsd()
            );

            salesAWeekBefore = salesAWeekBefore.add(
                    companiesOrderSalesDTO
                            .getOrders()
                            .getSalesAWeekBefore()
                            .getValue()
            );

            salesCurrent = salesCurrent.add(
                    companiesOrderSalesDTO
                            .getOrders()
                            .getSalesCurrent()
                            .getValue());
        }

        totalCompaniesOrderSalesDTO = BuildCompaniesOrderSalesMappers.addValueTotalCompaniesOrderSales(
                totalCompaniesOrderSalesDTO,
                newSalesAWeekBeforeUsd,
                newSalesCurrentUsd
        );

        return totalCompaniesOrderSalesDTO;
    }
}
