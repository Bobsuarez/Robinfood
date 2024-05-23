package com.robinfood.app.usecases.createorderuserdata;

import com.robinfood.app.mappers.input.OrderUserDataMapper;
import com.robinfood.core.dtos.request.transaction.RequestUserDTO;
import com.robinfood.core.entities.OrderUserDataEntity;
import com.robinfood.repository.orderuserdata.IOrderUserDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Implementation of ICreateOrderUserDataUseCase
 */
@Component
@Slf4j
public class CreateOrderUserDataUseCase implements ICreateOrderUserDataUseCase {

    private final IOrderUserDataRepository orderUserDataRepository;

    public CreateOrderUserDataUseCase(IOrderUserDataRepository orderUserDataRepository) {
        this.orderUserDataRepository = orderUserDataRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(List<RequestUserDTO> userDataDTOList) {

        log.info("Starting process to save order user data: [{}]", userDataDTOList);

        final List<OrderUserDataEntity> orderUserDataEntities = userDataDTOList.stream()
                .map(OrderUserDataMapper::toOrderUserDataEntity)
                .collect(Collectors.toList());

        orderUserDataRepository.saveAll(orderUserDataEntities);

        return CompletableFuture.completedFuture(Boolean.TRUE);
    }
}
