package com.robinfood.localserver.electronicbillbc.controllers;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;
import com.robinfood.localserver.commons.dtos.http.ApiResponseDTO;
import com.robinfood.localserver.commons.dtos.orders.billing.OrderBillingDTO;
import com.robinfood.localserver.commons.dtos.sathub.response.SatCFE;
import com.robinfood.localserver.electronicbillbc.mocks.dto.OrderBillingDTOMock;
import com.robinfood.localserver.electronicbillbc.mocks.dto.SatCFEMock;
import com.robinfood.localserver.electronicbillbc.usescases.ISendCancelSaleElectronicCouponUseCase;
import com.robinfood.localserver.electronicbillbc.usescases.SendFiscalElectronicCouponUseCase;
import java.util.TimeZone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class ElectronicBillControllerTest {

    @InjectMocks
    private ElectronicBillController electronicBillController;

    @Mock
    private SendFiscalElectronicCouponUseCase sendFiscalElectronicCouponUseCase;

    @Mock
    private ISendCancelSaleElectronicCouponUseCase sendCancelSaleElectronicCouponUseCase;

    private final OrderBillingDTOMock orderBillingDTOMock = new OrderBillingDTOMock();

    private ApiResponseDTO<Object> apiResponseDTO = new ApiResponseDTO<>();

    @Test
    void test_Success_Transformation_To_Sefaz() throws JsonProcessingException {

        OrderBillingDTO orderBillingDTO = orderBillingDTOMock.getDefaultOrderBillingDTO();
        when(sendFiscalElectronicCouponUseCase.invoke(orderBillingDTO)).thenReturn(new SatHubResultDto());

        ApiResponseDTO<SatHubResultDto> response = electronicBillController.sendElectronicBillToSefaz(orderBillingDTO);

        apiResponseDTO = ApiResponseDTO
                .builder()
                .code(HttpStatus.OK.value())
                .data(new SatHubResultDto())
                .locale(TimeZone.getTimeZone("Z").toString())
                .message(HttpStatus.OK.getReasonPhrase())
                .status(HttpStatus.OK.getReasonPhrase())
                .build();

        Assertions.assertEquals(apiResponseDTO.getCode(), response.getCode());
        Assertions.assertEquals(apiResponseDTO.getData(), response.getData());
    }

    @Test
    void test_Failed_Transformation_To_Sefaz() throws JsonProcessingException {

        OrderBillingDTO orderBillingDTO = orderBillingDTOMock.getDefaultOrderBillingDTO();
        when(sendFiscalElectronicCouponUseCase.invoke(orderBillingDTO)).thenReturn(new SatHubResultDto());

        ApiResponseDTO<SatHubResultDto> response = electronicBillController.sendElectronicBillToSefaz(orderBillingDTO);

        apiResponseDTO = ApiResponseDTO
                .builder()
                .code(HttpStatus.OK.value())
                .data(new SatHubResultDto())
                .locale(TimeZone.getTimeZone("Z").toString())
                .message(HttpStatus.OK.getReasonPhrase())
                .status(HttpStatus.OK.getReasonPhrase())
                .build();

        Assertions.assertEquals(apiResponseDTO.getCode(), response.getCode());
        Assertions.assertEquals(apiResponseDTO.getData(), response.getData());
    }

    @Test
    void test_Error_Transformation_To_Sefaz() throws JsonProcessingException {

        OrderBillingDTO orderBillingDTO = orderBillingDTOMock.getDefaultOrderBillingDTO();
        when(sendFiscalElectronicCouponUseCase.invoke(orderBillingDTO)).thenThrow(NullPointerException.class);

        Assertions.assertThrows(NullPointerException.class, () -> electronicBillController
                .sendElectronicBillToSefaz(orderBillingDTO));
    }

    @Test
    void test_Success_Transformation_Cancel_To_Sefaz() throws JsonProcessingException {

        SatCFE satCFE = new SatCFEMock().satCFE;

        when(sendCancelSaleElectronicCouponUseCase.invoke(satCFE, 1L)).thenReturn(new SatHubResultDto());

        ApiResponseDTO<SatHubResultDto> response = electronicBillController.sendCancelSaleBillToSefaz(satCFE, 1L);

        apiResponseDTO = ApiResponseDTO
                .builder()
                .code(HttpStatus.OK.value())
                .data(new SatHubResultDto())
                .locale(TimeZone.getTimeZone("Z").toString())
                .message(HttpStatus.OK.getReasonPhrase())
                .status(HttpStatus.OK.getReasonPhrase())
                .build();

        Assertions.assertEquals(apiResponseDTO.getCode(), response.getCode());
        Assertions.assertEquals(apiResponseDTO.getData(), response.getData());
    }

    @Test
    void test_Failed_Transformation_Cancel_To_Sefaz() throws JsonProcessingException {

        SatCFE satCFE = new SatCFEMock().satCFE;
        when(sendCancelSaleElectronicCouponUseCase.invoke(satCFE, 1L)).thenReturn(new SatHubResultDto());

        ApiResponseDTO<SatHubResultDto> response = electronicBillController.sendCancelSaleBillToSefaz(satCFE, 1L);

        apiResponseDTO = ApiResponseDTO
                .builder()
                .code(HttpStatus.OK.value())
                .data(new SatHubResultDto())
                .locale(TimeZone.getTimeZone("Z").toString())
                .message(HttpStatus.OK.getReasonPhrase())
                .status(HttpStatus.OK.getReasonPhrase())
                .build();

        Assertions.assertEquals(apiResponseDTO.getCode(), response.getCode());
        Assertions.assertEquals(apiResponseDTO.getData(), response.getData());
    }

    @Test
    void test_Error_Transformation_Cancel_To_Sefaz() throws JsonProcessingException {

        SatCFE satCFE = new SatCFEMock().satCFE;
        when(sendCancelSaleElectronicCouponUseCase.invoke(satCFE, 1L)).thenThrow(NullPointerException.class);

        Assertions.assertThrows(NullPointerException.class, () -> electronicBillController
                .sendCancelSaleBillToSefaz(satCFE, 1L));
    }
}
