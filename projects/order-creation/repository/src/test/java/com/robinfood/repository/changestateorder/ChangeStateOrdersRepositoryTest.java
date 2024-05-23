package com.robinfood.repository.changestateorder;

import com.robinfood.core.dtos.staterequestdto.StateChangeRequestDTO;
import com.robinfood.core.entities.changestateorderequestentities.ChangeStateOrderRequestEntity;
import com.robinfood.core.entities.changestateorderequestentities.ChangeStateOrderRespondEntity;
import com.robinfood.repository.changestateorders.ChangeStateOrdersRepository;
import com.robinfood.repository.changestateorders.IChangeStateOrderRemoteDataSource;
import com.robinfood.repository.changestateorders.IChangeStateOrdersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeStateOrdersRepositoryTest {

    private String token = "Prueba";
    @InjectMocks
    ChangeStateOrdersRepository changeStateOrdersRepository;

    @Mock
    IChangeStateOrderRemoteDataSource changeStateOrderRemoteDataSource;

    @Mock
    ModelMapper modelMapper;

    @Test
    void test_When_Send_Order_Change_State_Then_Return_Message() {

        ChangeStateOrderRequestEntity changeStateOrderRequestEntity = new ChangeStateOrderRequestEntity();
        ChangeStateOrderRespondEntity changeStateOrderRespondEntity = new ChangeStateOrderRespondEntity();
        CompletableFuture<ChangeStateOrderRespondEntity> resultado = CompletableFuture.completedFuture(
                changeStateOrderRespondEntity);

        when(modelMapper.map(any(),any())).thenReturn(changeStateOrderRequestEntity);
        when(changeStateOrderRemoteDataSource.invoke(changeStateOrderRequestEntity,token))
                .thenReturn(resultado);

        StateChangeRequestDTO stateChangeRequestDTO = new StateChangeRequestDTO();


        CompletableFuture<ChangeStateOrderRespondEntity> respond = changeStateOrdersRepository.invoke(
                stateChangeRequestDTO, token);

        assertEquals(resultado, respond);
    }
}
