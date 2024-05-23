package com.robinfood.app.usecases.createorderflagtogo;

import com.robinfood.app.mappers.input.OrderFlagTogoMappers;
import com.robinfood.core.dtos.request.order.OrderFlagTogoDTO;
import com.robinfood.core.entities.OrderFlagTogoEntity;
import com.robinfood.repository.orderflagtogo.IOrderFlagTogoRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of ICreateOrderFlagTogoUseCase
 */
@Component
@Slf4j
public class CreateOrderFlagTogoUseCase implements ICreateOrderFlagTogoUseCase {

    private final IOrderFlagTogoRepository orderFlagTogoRepository;

    public CreateOrderFlagTogoUseCase(IOrderFlagTogoRepository orderFlagTogoRepository) {
        this.orderFlagTogoRepository = orderFlagTogoRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(List<OrderFlagTogoDTO> orderFlagTogoDTOList) {

        log.info("Starting process to save order flag togo with data: {}", orderFlagTogoDTOList);

        final List<OrderFlagTogoEntity> orderFlagTogoEntityList = CollectionsKt.map(
                orderFlagTogoDTOList,
                OrderFlagTogoMappers::toOrderFlagTogoEntity
        );

        CollectionsKt.toList(
                orderFlagTogoRepository.saveAll(orderFlagTogoEntityList)
        );

        return CompletableFuture.completedFuture(true);
    }
}
