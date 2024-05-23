package com.robinfood.app.usecases.getstate;

import com.robinfood.app.mappers.OrderStateMappers;
import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.entities.StatusEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.status.IStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
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
                new GenericOrderBcException("status is no created"));

        if (status.getOrder().subtract(BigDecimal.valueOf(status.getParentId())).compareTo(BigDecimal.ZERO) >
                GlobalConstants.DEFAULT_INTEGER_VALUE) {

            StatusEntity parent = statusRepository.findById(status.getParentId()).orElseThrow(() ->
                    new GenericOrderBcException("substatus is no created"));

            return orderStateMappers.buildOrderState(status,parent);
        }

        return orderStateMappers.buildOrderState(status);
    }
}
