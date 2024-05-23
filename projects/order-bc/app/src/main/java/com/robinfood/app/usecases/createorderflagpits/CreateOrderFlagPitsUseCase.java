package com.robinfood.app.usecases.createorderflagpits;

import com.robinfood.app.mappers.input.OrderFlagPitsMappers;
import com.robinfood.core.dtos.request.order.OrderFlagPitsDTO;
import com.robinfood.core.entities.OrderFlagPitsEntity;
import com.robinfood.repository.orderflagpits.IOrderFlagPitsRepository;
import kotlin.collections.CollectionsKt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of ICreateOrderFlagPitsUseCase
 */
@Component
public class CreateOrderFlagPitsUseCase implements ICreateOrderFlagPitsUseCase {

    private final IOrderFlagPitsRepository orderFlagPitsRepository;

    public CreateOrderFlagPitsUseCase(IOrderFlagPitsRepository orderFlagPitsRepository) {
        this.orderFlagPitsRepository = orderFlagPitsRepository;
    }

    @Override
    public CompletableFuture<List<OrderFlagPitsDTO>> invoke(List<OrderFlagPitsDTO> orderFlagPitsDTOList) {

        final List<OrderFlagPitsEntity> orderFlagPitsEntityList = CollectionsKt.map(
                orderFlagPitsDTOList,
                OrderFlagPitsMappers::toOrderFlagPitsEntity
        );

        return CompletableFuture.completedFuture(
                CollectionsKt.map(
                        orderFlagPitsRepository.saveAll(orderFlagPitsEntityList),
                        OrderFlagPitsMappers::toOrderFlagPitsDTO
                )
        );
    }
}
