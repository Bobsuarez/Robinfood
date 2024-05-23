package com.robinfood.app.usecases.createorderflag;

import com.robinfood.app.mappers.input.InputIntermediateOrderFlagMappers;
import com.robinfood.core.dtos.request.order.IntermediateOrderFlagDTO;
import com.robinfood.core.entities.OrderFlagEntity;
import com.robinfood.repository.orderflag.IOrderFlagRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of ICreateOrderFlagUseCase
 */
@Component
@Slf4j
public class CreateOrderFlagUseCase implements ICreateOrderFlagUseCase {

    private final IOrderFlagRepository orderFlagRepository;

    public CreateOrderFlagUseCase(IOrderFlagRepository orderFlagRepository) {
        this.orderFlagRepository = orderFlagRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(List<IntermediateOrderFlagDTO> orderFlagDTOs) {

        log.info("Starting process to save order flag with data: {}", orderFlagDTOs);

        List<OrderFlagEntity> orderFlagEntities = CollectionsKt.map(
            orderFlagDTOs,
            InputIntermediateOrderFlagMappers::toOrderFlagEntity
        );

        orderFlagRepository.saveAll(orderFlagEntities);

        return CompletableFuture.completedFuture(Boolean.TRUE);
    }
}
