package com.robinfood.localorderbc.usecases.getorderexecutioncommandusecase;

import com.robinfood.localorderbc.dtos.OrderCommandExecutionDTO;
import com.robinfood.localorderbc.entities.OrderCommandExecutionEntity;
import com.robinfood.localorderbc.mappers.IOrderCommandExecutionMapper;
import com.robinfood.localorderbc.repositories.IOrderCommandExecutionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrderCommandExecutionUseCase implements IGetOrderCommandExecutionUseCase {

    private final IOrderCommandExecutionRepository orderCommandExecutionRepository;
    private final IOrderCommandExecutionMapper orderCommandExecutionMapper;

    public GetOrderCommandExecutionUseCase(IOrderCommandExecutionRepository orderCommandExecutionRepository,
                                           IOrderCommandExecutionMapper orderCommandExecutionMapper) {
        this.orderCommandExecutionRepository = orderCommandExecutionRepository;
        this.orderCommandExecutionMapper = orderCommandExecutionMapper;
    }

    @Override
    public List<OrderCommandExecutionDTO> invoke() {

        List<OrderCommandExecutionEntity> orderCommandExecutionEntityList = orderCommandExecutionRepository
                .findByReprocess(Boolean.TRUE);

        return orderCommandExecutionMapper
                .commandEntityLisToCommandExecutionDTOList(orderCommandExecutionEntityList);
    }
}
