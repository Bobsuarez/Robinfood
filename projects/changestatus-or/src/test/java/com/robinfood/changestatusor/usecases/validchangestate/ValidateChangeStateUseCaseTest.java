package com.robinfood.changestatusor.usecases.validchangestate;

import com.robinfood.changestatusor.dtos.ChangeOrderStatusDTO;
import com.robinfood.changestatusor.exceptions.GenericChangeStatusOrException;
import com.robinfood.changestatusor.usecases.validatechangestate.ValidateChangeStateUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ValidateChangeStateUseCaseTest {

    @InjectMocks
    ValidateChangeStateUseCase validateChangeStateUseCase;

    @Test
    void When_ChangeState_is_Valid_Transaction () {

        ChangeOrderStatusDTO changeOrderStatusDTO = new ChangeOrderStatusDTO();
        changeOrderStatusDTO.setOrderId(1L);
        assertTrue(validateChangeStateUseCase.invoke(changeOrderStatusDTO));
    }

    @Test
    void When_ChangeState_orderId_Null_Then_Should_Return_Exception () {

        ChangeOrderStatusDTO changeOrderStatusDTO = new ChangeOrderStatusDTO();
        assertThrows(GenericChangeStatusOrException.class, () -> {
            validateChangeStateUseCase.invoke(changeOrderStatusDTO);
        });
    }
    @Test
    void When_ChangeState_orderId_isEmpty_Then_Should_Return_Exception () {

        ChangeOrderStatusDTO changeOrderStatusDTO = new ChangeOrderStatusDTO();
        changeOrderStatusDTO.setDeliveryIntegrationId("");
        changeOrderStatusDTO.setOrderUuid("");
        assertThrows(GenericChangeStatusOrException.class, () -> {
            validateChangeStateUseCase.invoke(changeOrderStatusDTO);
        });
    }
}
