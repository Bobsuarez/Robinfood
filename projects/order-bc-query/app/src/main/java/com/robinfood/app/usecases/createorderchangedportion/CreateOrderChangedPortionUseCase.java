package com.robinfood.app.usecases.createorderchangedportion;

import com.robinfood.app.mappers.OrderChangedPortionMappers;
import com.robinfood.app.mappers.input.InputOrderChangedPortionMappers;
import com.robinfood.core.dtos.OrderChangedPortionDTO;
import com.robinfood.core.dtos.request.order.FinalProductPortionDTO;
import com.robinfood.core.entities.OrderChangedPortionEntity;
import com.robinfood.repository.orderchangedportions.IOrderChangedPortionRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ICreateOrderChangedPortionUseCase
 */
@Component
@Slf4j
public class CreateOrderChangedPortionUseCase implements ICreateOrderChangedPortionUseCase {

    private final IOrderChangedPortionRepository orderChangedPortionRepository;

    public CreateOrderChangedPortionUseCase(IOrderChangedPortionRepository orderChangedPortionRepository) {
        this.orderChangedPortionRepository = orderChangedPortionRepository;
    }

    @Override
    public List<OrderChangedPortionDTO> invoke(List<FinalProductPortionDTO> portionsReplacedDTO) {

        log.info("Starting process to save portions changed in the order with data: {}",
                portionsReplacedDTO);

        final List<OrderChangedPortionEntity> orderChangedPortionEntities = new ArrayList<>();

        for (FinalProductPortionDTO portionDTO : portionsReplacedDTO) {
            final OrderChangedPortionEntity orderChangedPortionEntity = InputOrderChangedPortionMappers
                    .toOrderChangedPortionEntity(portionDTO.getReplacementPortion());

            orderChangedPortionEntity.setOrderFinalProductPortionId(
                    portionDTO.getOrderFinalProductPortionId()
            );
            orderChangedPortionEntity.setOrderId(
                    portionDTO.getOrderId()
            );
            orderChangedPortionEntity.setOriginalPortionId(
                    portionDTO.getReplacementPortion().getOriginalReplacementPortionDTO().getId()
            );
            orderChangedPortionEntity.setOriginalPortionName(
                    portionDTO.getReplacementPortion().getOriginalReplacementPortionDTO().getName()
            );
            orderChangedPortionEntity.setOriginalProductId(
                    portionDTO.getReplacementPortion().getOriginalReplacementPortionDTO().getProduct().getId()
            );
            orderChangedPortionEntity.setOriginalProductName(
                    portionDTO.getReplacementPortion().getOriginalReplacementPortionDTO().getProduct().getName()
            );
            orderChangedPortionEntities.add(orderChangedPortionEntity);
        }

        return CollectionsKt.map(
                orderChangedPortionRepository.saveAll(orderChangedPortionEntities),
                OrderChangedPortionMappers::toOrderChangedPortionDTO
        );
    }
}
