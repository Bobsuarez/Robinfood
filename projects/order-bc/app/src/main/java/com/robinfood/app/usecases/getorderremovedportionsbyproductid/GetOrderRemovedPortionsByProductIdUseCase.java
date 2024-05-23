package com.robinfood.app.usecases.getorderremovedportionsbyproductid;

import com.robinfood.app.mappers.input.FinalProductRemovedPortionMappers;
import com.robinfood.core.dtos.OrderDetailRemovedPortionDTO;
import com.robinfood.repository.orderremovedportions.IOrderRemovedPortionRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of IGetOrderRemovedPortionsByProductIdUseCase
 */
@Component
@Slf4j
public class GetOrderRemovedPortionsByProductIdUseCase implements IGetOrderRemovedPortionsByProductIdUseCase {

    private final IOrderRemovedPortionRepository orderRemovedPortionRepository;

    public GetOrderRemovedPortionsByProductIdUseCase(IOrderRemovedPortionRepository orderRemovedPortionRepository) {
        this.orderRemovedPortionRepository = orderRemovedPortionRepository;
    }

    @Override
    public List<OrderDetailRemovedPortionDTO> invoke(List<Long> finalProductIds) {

        log.info("Starting process to order removed portions by product ids : {}", finalProductIds);

        return CollectionsKt.map(
                orderRemovedPortionRepository.findAllByOrderFinalProductIdIn(finalProductIds),
                FinalProductRemovedPortionMappers::toFinalProductRemovedPortionDTO
        );
    }
}
