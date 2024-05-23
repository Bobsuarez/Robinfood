package com.robinfood.app.usecases.validchangeorder;

import com.robinfood.core.dtos.staterequestdto.StateChangeRequestDTO;
import com.robinfood.core.exceptions.TransactionCreationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ValidChangeOrderUseCaseTest {

    @InjectMocks
    ValidChangeOrderUseCase validChangeOrderUseCase;

    @Test
    void test_When_Validated_Change_OrderId_Then_Return_True() {

        StateChangeRequestDTO stateChangeRequestDTO = new StateChangeRequestDTO();

        stateChangeRequestDTO.setOrderId(1L);

        Boolean result = validChangeOrderUseCase.invoke(stateChangeRequestDTO);

        assertTrue(result);
    }

    @Test
    void test_When_Validated_Change_OrderUuid_Then_Return_True() {

        StateChangeRequestDTO stateChangeRequestDTO = new StateChangeRequestDTO();

        stateChangeRequestDTO.setOrderUuid("asdasd");

        Boolean result = validChangeOrderUseCase.invoke(stateChangeRequestDTO);

        assertTrue(result);
    }

    @Test
    void test_When_Validated_Change_OrderUuid_Empty_Then_Return_True() {

        StateChangeRequestDTO stateChangeRequestDTO = new StateChangeRequestDTO();

        stateChangeRequestDTO.setOrderUuid("");
        stateChangeRequestDTO.setDeliveryIntegrationId("adesasd");

        Boolean result = validChangeOrderUseCase.invoke(stateChangeRequestDTO);

        assertTrue(result);
    }

    @Test
    void test_When_Validated_Change_DeleveryId_Then_Return_True() {

        StateChangeRequestDTO stateChangeRequestDTO = new StateChangeRequestDTO();

        stateChangeRequestDTO.setDeliveryIntegrationId("asdasdsa");

        Boolean result = validChangeOrderUseCase.invoke(stateChangeRequestDTO);

        assertTrue(result);
    }

    @Test
    void test_When_Validated_Change_Delivery_Empty_Then_Return_Exception() {

        StateChangeRequestDTO stateChangeRequestDTO = new StateChangeRequestDTO();

        stateChangeRequestDTO.setDeliveryIntegrationId("");

        assertThrows(TransactionCreationException.class, () -> {
            validChangeOrderUseCase.invoke(stateChangeRequestDTO);
        });
    }

    @Test
    void test_When_Validated_Change_Not_Identifier_Then_Return_Exception() {

        StateChangeRequestDTO stateChangeRequestDTO = new StateChangeRequestDTO();

        assertThrows(TransactionCreationException.class, () -> {
            validChangeOrderUseCase.invoke(stateChangeRequestDTO);
        });
    }

}
