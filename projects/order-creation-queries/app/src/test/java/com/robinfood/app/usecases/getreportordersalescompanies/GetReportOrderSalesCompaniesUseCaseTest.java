package com.robinfood.app.usecases.getreportordersalescompanies;

import com.google.gson.JsonObject;
import com.robinfood.app.mocks.BuildReportOrderSalesByCompaniesMock;
import com.robinfood.app.mocks.configurations.CompaniesDTOMock;
import com.robinfood.app.mocks.LambdaExchangeJsonObjectMock;
import com.robinfood.app.mocks.ResponseOrderActiveSalesDTOMock;
import com.robinfood.app.usecases.getactivesalestoorderbycompanies.GetActiveSalesToOrderByCompaniesUseCase;
import com.robinfood.app.usecases.getactivecompanies.GetActiveCompaniesUseCase;
import com.robinfood.app.usecases.getlambdaexchange.GetLambdaExchangeUseCase;
import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.dtos.orderSales.ResponseOrderActiveSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.ResponseOrderSalesDTO;
import com.robinfood.core.enums.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetReportOrderSalesCompaniesUseCaseTest {

    @Mock
    private GetActiveSalesToOrderByCompaniesUseCase getActiveSalesToOrderByCompaniesUseCase;

    @Mock
    private GetActiveCompaniesUseCase getConfigCompaniesUseCase;

    @Mock
    private GetLambdaExchangeUseCase getLambdaExchangeUseCase;

    @Mock
    private BuildResponseReportUseCase buildResponseReportUseCase;

    @Mock
    private BuildTotalCompaniesOrderSalesUseCase buildTotalCompaniesOrderSalesUseCase;

    @InjectMocks
    private GetReportOrderSalesCompaniesUseCase getReportOrderSalesCompaniesUseCase;

    @Test
    void Test_invoke_Should_Return_OK_When_InvokeTheUseCase() {

        when(getActiveSalesToOrderByCompaniesUseCase.invoke(
                anyList(),
                anyString(),
                anyList()
        )).thenReturn(new Result.Success<>(ResponseOrderActiveSalesDTOMock.getDataDefault()));

        when(getConfigCompaniesUseCase.invoke())
                .thenReturn(new Result.Success<>(CompaniesDTOMock.getDataDefault()));

        when(getLambdaExchangeUseCase.invoke(
                anyString(),
                anyString()
        )).thenReturn(new Result.Success<>(LambdaExchangeJsonObjectMock.getDataDefault()));

        when(buildResponseReportUseCase.invoke(
                any(ResponseOrderActiveSalesDTO.class),
                any(JsonObject.class),
                any(CompaniesDTO.class),
                anyString(),
                anyString()
        )).thenReturn(ResponseOrderSalesDTO.builder().build());

        when(buildTotalCompaniesOrderSalesUseCase.invoke(
                any(ResponseOrderSalesDTO.class)
        )).thenReturn(BuildReportOrderSalesByCompaniesMock.buildTotalCompaniesOrderSales());

        getReportOrderSalesCompaniesUseCase.invoke(
                LocalDateTime.now(),
                List.of(1)
        );

        verify(getActiveSalesToOrderByCompaniesUseCase)
                .invoke(
                        anyList(),
                        anyString(),
                        anyList()
                );

        verify(getConfigCompaniesUseCase)
                .invoke();

        verify(getLambdaExchangeUseCase)
                .invoke(
                        anyString(),
                        anyString()
                );

        verify(buildResponseReportUseCase)
                .invoke(
                        any(ResponseOrderActiveSalesDTO.class),
                        any(JsonObject.class),
                        any(CompaniesDTO.class),
                        anyString(),
                        anyString()
                );

        verify(buildTotalCompaniesOrderSalesUseCase)
                .invoke(
                        any(ResponseOrderSalesDTO.class)
                );
    }

    @Test
    void Test_invoke_Should_Return_Error_When_getLambdaExchanges() {

        when(getActiveSalesToOrderByCompaniesUseCase.invoke(
                anyList(),
                anyString(),
                anyList()
        )).thenReturn(new Result.Success<>(ResponseOrderActiveSalesDTOMock.getDataDefault()));

        when(getConfigCompaniesUseCase.invoke())
                .thenReturn(new Result.Success<>(CompaniesDTOMock.getDataDefault()));

        when(getLambdaExchangeUseCase.invoke(
                anyString(),
                anyString()
        )).thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(Exception.class, () -> {
            getReportOrderSalesCompaniesUseCase.invoke(
                    LocalDateTime.now(),
                    List.of(1)
            );
        });

        verify(getActiveSalesToOrderByCompaniesUseCase)
                .invoke(
                        anyList(),
                        anyString(),
                        anyList()
                );

        verify(getConfigCompaniesUseCase)
                .invoke();

        verify(getLambdaExchangeUseCase)
                .invoke(
                        anyString(),
                        anyString()
                );
    }
}
