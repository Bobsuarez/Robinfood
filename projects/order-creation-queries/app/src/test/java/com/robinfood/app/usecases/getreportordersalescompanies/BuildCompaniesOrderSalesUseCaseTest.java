package com.robinfood.app.usecases.getreportordersalescompanies;

import com.google.gson.JsonObject;
import com.robinfood.app.mocks.BuildReportOrderSalesByCompaniesMock;
import com.robinfood.app.mocks.configurations.CompaniesDTOMock;
import com.robinfood.app.mocks.LambdaExchangeJsonObjectMock;
import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.dtos.orderSales.OrderSalesByCompanyDTO;
import com.robinfood.core.dtos.reportordersalescompanies.CompaniesOrderSalesDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class BuildCompaniesOrderSalesUseCaseTest {

    @InjectMocks
    BuildCompaniesOrderSalesUseCase buildCompaniesOrderSalesUseCase;

    @Test
    void Test_invoke_Should_Return_OK_When_InvokeTheUseCase(){

        JsonObject lambdaExchange = LambdaExchangeJsonObjectMock.getDataDefault();
        OrderSalesByCompanyDTO orderSalesCompany = BuildReportOrderSalesByCompaniesMock.orderSalesByCompanyDTO();
        CompaniesDTO listCompanies = CompaniesDTOMock.getDataDefault();
        String currentDate = "2023/03/14";
        String previousDate = "2023/03/7";

        assertAll(() -> buildCompaniesOrderSalesUseCase.invoke(
                lambdaExchange,
                orderSalesCompany,
                listCompanies,
                currentDate,
                previousDate
        ));

        Optional<CompaniesOrderSalesDTO> response = buildCompaniesOrderSalesUseCase
                .invoke(
                        lambdaExchange,
                        orderSalesCompany,
                        listCompanies,
                        currentDate,
                        previousDate
                );

        assertTrue(response.get() instanceof CompaniesOrderSalesDTO);

        assertNotNull(response);
    }

    @Test
    void Test_invoke_Should_Return_OK_When_Company_Currency_Not_Found(){

        JsonObject lambdaExchange = LambdaExchangeJsonObjectMock.getDataDefaultWithCurrencyMXN();
        OrderSalesByCompanyDTO orderSalesCompany = BuildReportOrderSalesByCompaniesMock.orderSalesByCompanyDTO();
        CompaniesDTO listCompanies = CompaniesDTOMock.getDataDefault();
        String currentDate = "2023/03/14";
        String previousDate = "2023/03/7";

        final Optional<CompaniesOrderSalesDTO> response = buildCompaniesOrderSalesUseCase.invoke(
                lambdaExchange,
                orderSalesCompany,
                listCompanies,
                currentDate,
                previousDate
        );

        assertNotNull(response);
    }
}
