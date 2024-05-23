package com.robinfood.localorderbc.usecases.getorderexecutioncommandusecase;

import com.robinfood.localorderbc.dtos.OrderCommandExecutionDTO;
import com.robinfood.localorderbc.entities.OrderCommandExecutionEntity;
import com.robinfood.localorderbc.mappers.IOrderCommandExecutionMapper;
import com.robinfood.localorderbc.mocks.dtos.request.OrderCommandExecutionDTOMock;
import com.robinfood.localorderbc.mocks.entities.OrderCommandExecutionEntityMock;
import com.robinfood.localorderbc.repositories.IOrderCommandExecutionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrderCommandExecutionUseCaseTest {

    @Mock
    private IOrderCommandExecutionRepository orderCommandExecutionRepository;

    @Mock
    private IOrderCommandExecutionMapper orderCommandExecutionMapper;

    @InjectMocks
    private GetOrderCommandExecutionUseCase getOrderCommandExecutionUseCase;

    final List<OrderCommandExecutionDTO> orderCommandExecutionDTOList =
            new OrderCommandExecutionDTOMock().orderCommandExecutionDTOList;

    final List<OrderCommandExecutionEntity> orderCommandExecutionEntityList =
            new OrderCommandExecutionEntityMock().orderCommandExecutionEntityList;

    @Test
    void Get_Order_Command_Execution_Success() {

        when(orderCommandExecutionRepository.findByReprocess(Boolean.TRUE))
                .thenReturn(orderCommandExecutionEntityList);

        when(orderCommandExecutionMapper.commandEntityLisToCommandExecutionDTOList(orderCommandExecutionEntityList))
                .thenReturn(orderCommandExecutionDTOList);

        List<OrderCommandExecutionDTO> orderCommandExecutionResultDTOList = getOrderCommandExecutionUseCase.invoke();

       Assertions.assertEquals(orderCommandExecutionResultDTOList, orderCommandExecutionDTOList);

    }

}
