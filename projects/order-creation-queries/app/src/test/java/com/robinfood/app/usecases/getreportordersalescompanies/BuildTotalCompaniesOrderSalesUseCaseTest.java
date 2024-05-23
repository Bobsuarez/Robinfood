package com.robinfood.app.usecases.getreportordersalescompanies;

import com.robinfood.app.mocks.BuildReportOrderSalesByCompaniesMock;
import com.robinfood.core.dtos.reportordersalescompanies.ResponseOrderSalesDTO;
import com.robinfood.core.dtos.reportordersalescompanies.TotalCompaniesOrderSalesDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuildTotalCompaniesOrderSalesUseCaseTest {

    @InjectMocks
    BuildTotalCompaniesOrderSalesUseCase buildTotalCompaniesOrderSalesUseCase;

    @Test
    void Test_invoke_Should_Return_OK_When_InvokeTheUseCase(){
        ResponseOrderSalesDTO responseOrderSalesDTO = BuildReportOrderSalesByCompaniesMock.responseOrderSalesDTO();

        assertAll(() -> buildTotalCompaniesOrderSalesUseCase.invoke(
                responseOrderSalesDTO
        ));

        TotalCompaniesOrderSalesDTO response = buildTotalCompaniesOrderSalesUseCase
                .invoke(
                        responseOrderSalesDTO
                );

        assertTrue(response instanceof
                TotalCompaniesOrderSalesDTO);

        assertEquals(
                BuildReportOrderSalesByCompaniesMock.buildTotalCompaniesOrderSalesFinal(),
                response
        );

    }
}
