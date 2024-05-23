package com.robinfood.app.usecases.getreportordersalescompanies;

import com.google.gson.JsonObject;
import com.robinfood.app.mocks.BuildReportOrderSalesByCompaniesMock;
import com.robinfood.app.mocks.configurations.CompaniesDTOMock;
import com.robinfood.app.mocks.LambdaExchangeJsonObjectMock;
import com.robinfood.app.mocks.ResponseOrderActiveSalesDTOMock;
import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.dtos.orderSales.OrderSalesByCompanyDTO;
import com.robinfood.core.dtos.orderSales.ResponseOrderActiveSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.ResponseOrderSalesDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuildResponseReportUseCaseTest {

    @Mock
    BuildCompaniesOrderSalesUseCase buildCompaniesOrderSalesUseCase;

    @InjectMocks
    BuildResponseReportUseCase buildResponseReportUseCase;

    @Test
    void Test_invoke_Should_Return_OK_When_InvokeTheUseCase(){
        ResponseOrderActiveSalesDTO responseOrderActiveSalesDTO = ResponseOrderActiveSalesDTOMock.getDataDefault();
        JsonObject lambdaExchange = LambdaExchangeJsonObjectMock.getDataDefault();
        CompaniesDTO listCompanies = CompaniesDTOMock.getDataDefault();
        String currentDate = "2023/03/14";
        String previousDate = "2023/03/7";

        when(buildCompaniesOrderSalesUseCase.invoke(
                any( JsonObject.class),
                any(OrderSalesByCompanyDTO.class),
                any(CompaniesDTO.class),
                anyString(),
                anyString()
        )).thenReturn(BuildReportOrderSalesByCompaniesMock.buildCompaniesOrderSales());

        assertAll(() -> buildResponseReportUseCase.invoke(
                responseOrderActiveSalesDTO,
                lambdaExchange,
                listCompanies,
                currentDate,
                previousDate
        ));

        ResponseOrderSalesDTO response = buildResponseReportUseCase
                .invoke(
                        responseOrderActiveSalesDTO,
                        lambdaExchange,
                        listCompanies,
                        currentDate,
                        previousDate
                );

        assertTrue(response instanceof ResponseOrderSalesDTO);

        assertEquals(
                BuildReportOrderSalesByCompaniesMock.buildResponseReport(),
                response
        );
    }
}
