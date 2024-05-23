package com.robinfood.localserver.electronicbillbc.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localserver.commons.dtos.electronicbill.FiscalElectronicCouponDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.cancelsale.CancelElectronicCouponDTO;
import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;
import com.robinfood.localserver.commons.dtos.http.ApiResponseDTO;
import com.robinfood.localserver.commons.entities.electronicbill.FiscalElectronicCouponEntity;
import com.robinfood.localserver.commons.entities.electronicbill.cancelsale.CancelElectronicCouponEntity;
import com.robinfood.localserver.commons.exceptions.HttpClientException;
import com.robinfood.localserver.commons.mappers.electronicbill.ICancelElectronicCouponMapperImpl;
import com.robinfood.localserver.commons.mappers.electronicbill.IFiscalElectronicCouponMapperImpl;
import com.robinfood.localserver.electronicbillbcsaopaulo.controllers.v1.sathubcontroller.SatHubController;
import com.robinfood.localserver.electronicbillbcsaopaulo.exceptions.BusinessRuleException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        ElectronicBillSaoPauloDataSource.class,
        IFiscalElectronicCouponMapperImpl.class,
        ICancelElectronicCouponMapperImpl.class
})
class ElectronicBillSaoPauloDataSourceTest {

    @MockBean
    private SatHubController satHubController;

    @MockBean
    private ObjectMapper objectMapper;

    @Autowired
    private ElectronicBillSaoPauloDataSource electronicBillSaoPauloDataSource;

    @Test
    void test_When_Electronic_Bill_SaoPaulo_sendFiscalElectronicCoupon_Is_OK() throws Exception {

        ApiResponseDTO<SatHubResultDto> responseDTO = new ApiResponseDTO<>(
                HttpStatus.OK.value(),
                new SatHubResultDto(),
                LocalDateTime.now().toString(),
                "Response ok",
                HttpStatus.OK.getReasonPhrase()
        );

        when(satHubController.sendSaleData(any(FiscalElectronicCouponDTO.class), anyLong())).thenReturn(responseDTO);

        SatHubResultDto response = electronicBillSaoPauloDataSource.sendFiscalElectronicCoupon(
                new FiscalElectronicCouponEntity(), 1L);

        assertNotNull(response);
    }

    @Test
    void test_When_Local_Order_Or_DataSource_sendOrderDetail_Is_Fail()
            throws BusinessRuleException, HttpClientException, JsonProcessingException {

        ApiResponseDTO<SatHubResultDto> responseDTO = new ApiResponseDTO<>(
                HttpStatus.BAD_REQUEST.value(),
                new SatHubResultDto(),
                LocalDateTime.now().toString(),
                "Response Fail",
                HttpStatus.BAD_REQUEST.getReasonPhrase()
        );

        when(satHubController.sendSaleData(any(FiscalElectronicCouponDTO.class), anyLong())).thenReturn(responseDTO);

        Exception exceptionResponse = assertThrows(Exception.class,
                () -> electronicBillSaoPauloDataSource.sendFiscalElectronicCoupon(
                        new FiscalElectronicCouponEntity(), 1L));

        assertEquals("Response Fail", exceptionResponse.getMessage());
    }

    @Test
    void test_When_Local_Order_Or_DataSource_sendOrderDetail_Convert_To_Json_Fail()
            throws BusinessRuleException, HttpClientException, JsonProcessingException {

        when(objectMapper.writeValueAsString(any(FiscalElectronicCouponEntity.class))).thenThrow(
                new RuntimeException("Error"));

        ApiResponseDTO<SatHubResultDto> responseDTO = new ApiResponseDTO<>(
                HttpStatus.OK.value(),
                new SatHubResultDto(),
                LocalDateTime.now().toString(),
                "Response ok",
                HttpStatus.OK.getReasonPhrase()
        );

        when(satHubController.sendSaleData(any(FiscalElectronicCouponDTO.class), anyLong())).thenReturn(responseDTO);

        SatHubResultDto response = electronicBillSaoPauloDataSource.sendFiscalElectronicCoupon(
                new FiscalElectronicCouponEntity(), 1L);

        assertNotNull(response);
    }

    @Test
    void test_When_Electronic_Bill_SaoPaulo_sendCancelFiscalElectronicCoupon_Is_OK() throws Exception {

        ApiResponseDTO<SatHubResultDto> responseDTO = new ApiResponseDTO<>(
                HttpStatus.OK.value(),
                new SatHubResultDto(),
                LocalDateTime.now().toString(),
                "Response ok",
                HttpStatus.OK.getReasonPhrase()
        );

        when(satHubController.sendCancelSaleData(any(CancelElectronicCouponDTO.class), anyLong(), anyString()))
                .thenReturn(responseDTO);

        SatHubResultDto response = electronicBillSaoPauloDataSource.sendCancelElectronicCoupon(
                new CancelElectronicCouponEntity(), 1L,"12345");

        assertNotNull(response);
    }

    @Test
    void test_When_Local_Order_Or_DataSource_sendCancelOrderDetail_Is_Fail()
            throws BusinessRuleException, HttpClientException, JsonProcessingException {

        ApiResponseDTO<SatHubResultDto> responseDTO = new ApiResponseDTO<>(
                HttpStatus.BAD_REQUEST.value(),
                new SatHubResultDto(),
                LocalDateTime.now().toString(),
                "Response Fail",
                HttpStatus.BAD_REQUEST.getReasonPhrase()
        );

        when(satHubController.sendCancelSaleData(any(CancelElectronicCouponDTO.class), anyLong(), anyString()))
                .thenReturn(responseDTO);

        Exception exceptionResponse = assertThrows(Exception.class,
                () -> electronicBillSaoPauloDataSource.sendCancelElectronicCoupon(
                        new CancelElectronicCouponEntity(), 1L, "12345"));

        assertEquals("Response Fail", exceptionResponse.getMessage());
    }

    @Test
    void test_When_Local_Order_Or_DataSource_sendCancelOrderDetail_Convert_To_Json_Fail()
            throws BusinessRuleException, HttpClientException, JsonProcessingException {

        when(objectMapper.writeValueAsString(any(CancelElectronicCouponEntity.class))).thenThrow(
                new RuntimeException("Error"));

        ApiResponseDTO<SatHubResultDto> responseDTO = new ApiResponseDTO<>(
                HttpStatus.OK.value(),
                new SatHubResultDto(),
                LocalDateTime.now().toString(),
                "Response ok",
                HttpStatus.OK.getReasonPhrase()
        );

        when(satHubController.sendCancelSaleData(any(CancelElectronicCouponDTO.class), anyLong(), anyString()))
                .thenReturn(responseDTO);

        SatHubResultDto response = electronicBillSaoPauloDataSource.sendCancelElectronicCoupon(
                new CancelElectronicCouponEntity(), 1L,"12345");

        assertNotNull(response);
    }
}
