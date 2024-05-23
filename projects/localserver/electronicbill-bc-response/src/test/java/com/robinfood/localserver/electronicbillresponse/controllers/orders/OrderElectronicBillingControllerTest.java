package com.robinfood.localserver.electronicbillresponse.controllers.orders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.robinfood.localserver.commons.dtos.electronicbill.RequestElectronicBillingDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.ResponseElectronicBillingDTO;
import com.robinfood.localserver.commons.dtos.http.ApiResponseDTO;
import com.robinfood.localserver.electronicbillresponse.mocks.dtos.RequestElectronicBillingDTOMock;
import com.robinfood.localserver.electronicbillresponse.mocks.dtos.ResponseElectronicBillingDTOMock;
import com.robinfood.localserver.electronicbillresponse.usecase.createelectronicbilling.CreateOrderElectronicBillUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderElectronicBillingControllerTest {

    final RequestElectronicBillingDTO createElectronicBillingRequestDTOMock =
            new RequestElectronicBillingDTOMock().requestElectronicBillingDTO;

    final ResponseElectronicBillingDTO createElectronicBillingResponseDTOMock =
            new ResponseElectronicBillingDTOMock().responseElectronicBillingDTO;

    @InjectMocks
    OrderElectronicBillingController orderElectronicBillingController;

    @Mock
    CreateOrderElectronicBillUseCase orderElectronicBillUseCase;

    @Test
    void test_CreateOrderElectronicBilling() {

        ApiResponseDTO<ResponseElectronicBillingDTO> response = new ApiResponseDTO<>();
        response.setData(createElectronicBillingResponseDTOMock);

        ApiResponseDTO<Boolean> responseOrderElectronicBillingDTO = orderElectronicBillingController.createOrderElectronicBilling(
                createElectronicBillingRequestDTOMock);

        assertEquals(responseOrderElectronicBillingDTO.getMessage(), response.getMessage());
    }
}
