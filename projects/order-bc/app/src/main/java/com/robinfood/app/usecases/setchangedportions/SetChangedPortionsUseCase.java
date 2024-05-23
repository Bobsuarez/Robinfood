package com.robinfood.app.usecases.setchangedportions;

import com.robinfood.app.mappers.OrderChangedPortionMappers;
import com.robinfood.app.mappers.OrderFinalProductPortionMappers;
import com.robinfood.core.dtos.DetailChangedPortionDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductPortionDTO;
import com.robinfood.core.entities.OrderChangedPortionEntity;
import com.robinfood.core.entities.OrderFinalProductPortionEntity;
import com.robinfood.repository.orderchangedportions.IOrderChangedPortionRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ISetChangedPortionsUseCase
 */
@Component
@Slf4j
public class SetChangedPortionsUseCase implements ISetChangedPortionsUseCase {

    private final IOrderChangedPortionRepository orderChangedPortionRepository;

    public SetChangedPortionsUseCase(IOrderChangedPortionRepository orderChangedPortionRepository) {
        this.orderChangedPortionRepository = orderChangedPortionRepository;
    }

    @Override
    public List<GetOrderDetailFinalProductPortionDTO> invoke(
            List<OrderFinalProductPortionEntity> orderFinalProductPortionEntities
    ) {

        log.info(
                "Starting set changed portions for list order final product portion entity: {}",
                orderFinalProductPortionEntities
        );

        final List<Long> portionIds = CollectionsKt.mapNotNull(
                orderFinalProductPortionEntities,
                (OrderFinalProductPortionEntity orderFinalProductPortionEntity) -> {
                    if (Boolean.TRUE.equals(orderFinalProductPortionEntity.getChangedProduct())) {
                        return orderFinalProductPortionEntity.getId();
                    }
                    return null;
                }
        );
        final List<OrderChangedPortionEntity> portionsChanged =
                orderChangedPortionRepository.findByOrderFinalProductPortionIdIn(portionIds);
        final List<GetOrderDetailFinalProductPortionDTO> portionOrderFinalProductDTOS = new ArrayList<>();
        for (OrderFinalProductPortionEntity orderFinalProductPortionEntity: orderFinalProductPortionEntities) {
            final GetOrderDetailFinalProductPortionDTO getOrderDetailFinalProductPortionDTO =
                    OrderFinalProductPortionMappers.toGetOrderDetailFinalProductPortionDTO(
                            orderFinalProductPortionEntity
                    );
            for (OrderChangedPortionEntity orderChangedPortionEntity: portionsChanged) {
                if (orderFinalProductPortionEntity.getId()
                        .equals(orderChangedPortionEntity.getOrderFinalProductPortionId())) {
                    final DetailChangedPortionDTO detailChangedPortionDTO = OrderChangedPortionMappers
                            .toDetailChangedPortionDTO(orderChangedPortionEntity);
                    getOrderDetailFinalProductPortionDTO.setChangedPortion(detailChangedPortionDTO);
                    break;
                }
            }
            portionOrderFinalProductDTOS.add(getOrderDetailFinalProductPortionDTO);
        }
        return portionOrderFinalProductDTOS;
    }
}
