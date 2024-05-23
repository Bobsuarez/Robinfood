package com.robinfood.app.usecases.createorderflagsubmarine;

import com.robinfood.app.mappers.input.InputOrderFlagSubmarineMappers;
import com.robinfood.core.dtos.request.order.OrderFlagSubmarineDTO;
import com.robinfood.core.entities.OrderFlagSubmarineEntity;
import com.robinfood.repository.orderflagsubmarine.IOrderFlagSubmarineRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of ICreateOrderFlagSubmarineUseCase
 */
@Component
@Slf4j
public class CreateOrderFlagSubmarineUseCase implements ICreateOrderFlagSubmarineUseCase {

    private final IOrderFlagSubmarineRepository orderFlagSubmarineRepository;

    public CreateOrderFlagSubmarineUseCase(IOrderFlagSubmarineRepository orderFlagSubmarineRepository) {
        this.orderFlagSubmarineRepository = orderFlagSubmarineRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(
        List<OrderFlagSubmarineDTO> orderFlagSubmarineDTOList) {

        log.info("Starting process to save order flag submarine with data: [{}]",
            orderFlagSubmarineDTOList);

        final List<OrderFlagSubmarineEntity> orderFlagSubmarineEntityList = CollectionsKt.map(
            orderFlagSubmarineDTOList,
            InputOrderFlagSubmarineMappers::toOrderFlagSubmarineEntity
        );

        orderFlagSubmarineRepository.saveAll(orderFlagSubmarineEntityList);

        return CompletableFuture.completedFuture(true);
    }
}
