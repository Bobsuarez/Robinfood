package com.robinfood.app.usecases.getorderdetailfinalproductportion;

import com.robinfood.app.usecases.setchangedportions.ISetChangedPortionsUseCase;
import com.robinfood.core.dtos.GetOrderDetailFinalProductPortionDTO;
import com.robinfood.core.entities.OrderFinalProductPortionEntity;
import com.robinfood.repository.orderfinalproductportions.IOrderFinalProductPortionRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Implementation of IGetOrderDetailPortionsByProductIdsUseCase
 */
@Component
@Slf4j
public class GetOrderDetailPortionsByProductIdsUseCase implements IGetOrderDetailPortionsByProductIdsUseCase {

    private final IOrderFinalProductPortionRepository orderFinalProductPortionRepository;
    private final ISetChangedPortionsUseCase setChangedPortionsUseCase;

    public GetOrderDetailPortionsByProductIdsUseCase(
            IOrderFinalProductPortionRepository orderFinalProductPortionRepository,
            ISetChangedPortionsUseCase setChangedPortionsUseCase
    ) {
        this.orderFinalProductPortionRepository = orderFinalProductPortionRepository;
        this.setChangedPortionsUseCase = setChangedPortionsUseCase;
    }

    @Override
    public Map<Long, List<GetOrderDetailFinalProductPortionDTO>> invoke(
            List<Long> orderFinalProductIds
    ) {

        log.info("Starting process to get order detail Portions by product ids: {}", orderFinalProductIds);

        final List<OrderFinalProductPortionEntity> orderFinalProductPortionEntities = orderFinalProductPortionRepository
                .findOrderFinalProductPortionEntityByOrderFinalProductIdIn(orderFinalProductIds);

        final List<GetOrderDetailFinalProductPortionDTO> portionOrderFinalProductDTOS = setChangedPortionsUseCase
                .invoke(orderFinalProductPortionEntities);

        return CollectionsKt.groupBy(
                portionOrderFinalProductDTOS, GetOrderDetailFinalProductPortionDTO::getFinalProductId
        );
    }
}
