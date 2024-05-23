package com.robinfood.app.usecases.getreportordersalescompanies;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.robinfood.core.dtos.configuration.CompanyDTO;
import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.dtos.orderSales.OrderSalesByCompanyDTO;
import com.robinfood.core.dtos.reportordersalescompanies.CompaniesOrderSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.CountryDTO;
import com.robinfood.core.dtos.reportordersalescompanies.OrderSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.PercentageSalesDifferenceDTO;
import com.robinfood.core.dtos.reportordersalescompanies.SalesAWeekBeforeDTO;
import com.robinfood.core.dtos.reportordersalescompanies.SalesCurrentDTO;
import com.robinfood.core.mappers.BuildCompaniesOrderSalesMappers;
import com.robinfood.core.utilities.CalculatorPercentageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class BuildCompaniesOrderSalesUseCase implements IBuildCompaniesOrderSalesUseCase {

    @Override
    public Optional<CompaniesOrderSalesDTO> invoke(
            JsonObject lambdaExchange,
            OrderSalesByCompanyDTO orderSalesCompany,
            CompaniesDTO listCompanies,
            String currentDate,
            String previousDate
    ) {
        final String KEY_DATES = "dates";
        final String KEY_EXCHANGE_RATES = "exchangeRates";
        final String KEY_RATES = "rates";

        final Set<Map.Entry<String, JsonElement>> getRates = lambdaExchange.get(KEY_DATES).getAsJsonObject()
                .get(currentDate).getAsJsonObject()
                .get(KEY_EXCHANGE_RATES).getAsJsonObject()
                .get("USD").getAsJsonObject()
                .get(KEY_RATES).getAsJsonObject().entrySet();

        final CompanyDTO companiesDTO = listCompanies.getCompanies()
                .stream()
                .filter((CompanyDTO company) -> company.getId().equals(orderSalesCompany.getId()))
                .findFirst()
                .orElseThrow();

        final Boolean filterGetRates = getRates.stream().anyMatch(getRate -> getRate.getKey().equals(
                companiesDTO.getCurrency_type()
        ));

        if (Boolean.FALSE.equals(filterGetRates)) {
            return Optional.empty();
        }

        BigDecimal currentDateTotal = lambdaExchange.get(KEY_DATES).getAsJsonObject()
                .get(currentDate).getAsJsonObject()
                .get(KEY_EXCHANGE_RATES).getAsJsonObject()
                .get("USD").getAsJsonObject()
                .get(KEY_RATES).getAsJsonObject()
                .get(companiesDTO.getCurrency_type())
                .getAsBigDecimal();

        BigDecimal previousDateTotal = lambdaExchange.get(KEY_DATES).getAsJsonObject()
                .get(previousDate).getAsJsonObject()
                .get(KEY_EXCHANGE_RATES).getAsJsonObject()
                .get("USD").getAsJsonObject()
                .get(KEY_RATES).getAsJsonObject()
                .get(companiesDTO.getCurrency_type())
                .getAsBigDecimal();

        SalesAWeekBeforeDTO salesAWeekBeforeDTO = BuildCompaniesOrderSalesMappers
                .buildSalesAWeekBeforeDTO(
                        orderSalesCompany,
                        previousDateTotal
                );

        SalesCurrentDTO salesCurrentDTO = BuildCompaniesOrderSalesMappers
                .buildSalesCurrentDTO(
                        orderSalesCompany,
                        currentDateTotal
                );

        PercentageSalesDifferenceDTO percentageSalesDifferenceDTO = PercentageSalesDifferenceDTO.builder()
                .value(CalculatorPercentageUtil.getPercentageDifference(
                        salesCurrentDTO.getValue(),
                        salesAWeekBeforeDTO.getValue()))
                .build();

        OrderSalesDTO orderSales = OrderSalesDTO.builder()
                .salesAWeekBefore(salesAWeekBeforeDTO)
                .salesCurrent(salesCurrentDTO)
                .percentageSalesDifference(percentageSalesDifferenceDTO)
                .build();

        final CountryDTO countryDTO = CountryDTO.builder()
                .id(companiesDTO.getCountry().getId())
                .name(companiesDTO.getCountry().getName())
                .build();

        return Optional.of(
                CompaniesOrderSalesDTO.builder()
                        .country(countryDTO)
                        .id(orderSalesCompany.getId())
                        .name(companiesDTO.getName())
                        .currency(companiesDTO.getCurrency_type())
                        .orders(orderSales)
                        .build()
        );
    }
}
