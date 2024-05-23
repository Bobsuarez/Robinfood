package com.robinfood.changestatusbc.usecases.getstate;

import com.robinfood.changestatusbc.dtos.OrderStateDTO;
import com.robinfood.changestatusbc.entities.StatusEntity;
import com.robinfood.changestatusbc.exceptions.GenericChangeStatusBcException;
import com.robinfood.changestatusbc.mappers.OrderStateMappers;
import com.robinfood.changestatusbc.repositories.status.IStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.robinfood.changestatusbc.configs.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

@Service
@Slf4j
public class GetStateOrderWithCodeUseCase implements IGetStateOrderWithCodeUseCase {

    private final IStatusRepository statusRepository;
    private final OrderStateMappers orderStateMappers;

    public GetStateOrderWithCodeUseCase(IStatusRepository statusRepository, OrderStateMappers orderStateMappers) {
        this.statusRepository = statusRepository;
        this.orderStateMappers = orderStateMappers;
    }

    @Override
    public OrderStateDTO invoke(String code) {

        log.info("Getting state with code: {}", code);

        StatusEntity status = statusRepository.findByCode(code).orElseThrow(() ->
                new GenericChangeStatusBcException("Status is no created"));

        if (
                status.getOrder().subtract(BigDecimal.valueOf(status.getParentId())).compareTo(BigDecimal.ZERO) >
                DEFAULT_INTEGER_VALUE
        ) {

            StatusEntity parent = statusRepository.findById(status.getParentId())
                    .orElseThrow(() -> new GenericChangeStatusBcException("Sub status is not created"));

            return orderStateMappers.buildOrderStateWithParent(status, parent);
        }

        return orderStateMappers.buildOrderState(status);
    }
}
