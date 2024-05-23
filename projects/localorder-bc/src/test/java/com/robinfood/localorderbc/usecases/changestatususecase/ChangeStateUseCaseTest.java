package com.robinfood.localorderbc.usecases.changestatususecase;


import com.robinfood.localorderbc.dtos.request.ChangeStateDTO;
import com.robinfood.localorderbc.entities.changestate.ChangeStateEntity;
import com.robinfood.localorderbc.entities.token.TokenModel;
import com.robinfood.localorderbc.mappers.IChangeStateMapper;
import com.robinfood.localorderbc.repositories.changestate.IChangeStateRepository;
import com.robinfood.localorderbc.usecases.gettokenuser.IGetOrchestratorTokenUserUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangeStateUseCaseTest {

    @Mock
    private IGetOrchestratorTokenUserUseCase getOrchestratorTokenUserUseCase;

    @Mock
    private IChangeStateRepository changeStateRepository;

    @Mock
    private IChangeStateMapper changeStateMapper;

    @InjectMocks
    private ChangeStateUseCase changeStateUseCase;

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


    @Test
    void When_Change_State_UseCase_Success(){

        when(getOrchestratorTokenUserUseCase.invoke())
                .thenReturn(TokenModel.builder()
                        .accessToken("token")
                        .expiresIn(1L)
                        .build());

        when(changeStateRepository.responseChangeState("token", changeStateDTO))
                .thenReturn(changeStateEntity);

        when(changeStateMapper.changeStateDEntityChangeStateExecutionDTO(changeStateEntity))
                .thenReturn(changeStateDTO);

        ChangeStateDTO changeStateResultDTO = changeStateUseCase.invoke(changeStateDTO);

        assertNotNull(changeStateResultDTO);

    }

}
