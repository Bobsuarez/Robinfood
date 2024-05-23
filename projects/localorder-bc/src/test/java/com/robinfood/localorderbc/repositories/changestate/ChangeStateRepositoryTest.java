package com.robinfood.localorderbc.repositories.changestate;

import com.robinfood.localorderbc.configs.apis.OrderCreationApi;
import com.robinfood.localorderbc.dtos.request.ChangeStateDTO;
import com.robinfood.localorderbc.entities.APIResponseEntity;
import com.robinfood.localorderbc.entities.changestate.ChangeStateEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangeStateRepositoryTest {

    @Mock
    private OrderCreationApi orderCreationApi;

    @InjectMocks
    private ChangeStateRepository changeStateRepository;

    ChangeStateDTO changeStateDTO = ChangeStateDTO.builder()
            .brandId("1")
            .notes("note")
            .orderId("12345")
            .userId(1L)
            .statusCode("ORDER_READY_TO_DELIVERED")
            .origin("AUTOGESTION")
            .build();

    ChangeStateEntity changeStateEntity = ChangeStateEntity.builder()
            .brandId("1")
            .notes("note")
            .orderId("12345")
            .userId(1L)
            .statusCode("ORDER_READY_TO_DELIVERED")
            .origin("AUTOGESTION")
            .build();

    APIResponseEntity apiResponseEntity = APIResponseEntity.builder()
            .code(200)
            .data(changeStateEntity)
            .locale("locale")
            .message("OK")
            .status("ok")
            .build();

    @Test
    void Order_Change_State_Repository_Success()  {

        when(orderCreationApi.changeState("token", changeStateDTO))
                .thenReturn(apiResponseEntity);

        ChangeStateEntity changeStateResultEntity = changeStateRepository
                .responseChangeState("token", changeStateDTO);

        assertEquals(changeStateEntity, changeStateResultEntity);
    }
}
