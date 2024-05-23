package com.robinfood.app.usecases.createorderremovedportion;

import com.robinfood.app.mappers.input.FinalProductRemovedPortionMappers;
import com.robinfood.core.dtos.request.order.FinalProductRemovedPortionDTO;
import com.robinfood.core.entities.FinalProductRemovedPortionEntity;
import com.robinfood.repository.orderremovedportions.IOrderRemovedPortionRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of ICreateOrderRemovedPortionUseCase
 */
@Component
@Slf4j
public class CreateOrderRemovedPortionUseCase implements ICreateOrderRemovedPortionUseCase {

    private final IOrderRemovedPortionRepository orderRemovedPortionRepository;

    public CreateOrderRemovedPortionUseCase(IOrderRemovedPortionRepository orderRemovedPortionRepository) {
        this.orderRemovedPortionRepository = orderRemovedPortionRepository;
    }

    @Override
    public CompletableFuture<Boolean> invoke(
        List<FinalProductRemovedPortionDTO> removedPortionsDTO) {

        log.info("Starting process to save portions removed in the order with data: {}",
            removedPortionsDTO);

        final List<FinalProductRemovedPortionEntity> orderRemovedPortionEntities = CollectionsKt.map(
            removedPortionsDTO,
            FinalProductRemovedPortionMappers::toFinalProductRemovedPortionEntity
        );

        CollectionsKt.toList(
            orderRemovedPortionRepository.saveAll(orderRemovedPortionEntities)
        );

        return CompletableFuture.completedFuture(Boolean.TRUE);
    }
}
