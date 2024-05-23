package com.robinfood.app.usecases.createorderfinalproductportion;

import com.robinfood.app.mappers.input.FinalProductPortionMappers;
import com.robinfood.app.usecases.createorderchangedportion.ICreateOrderChangedPortionUseCase;
import com.robinfood.core.dtos.request.order.FinalProductPortionDTO;
import com.robinfood.core.entities.OrderFinalProductPortionEntity;
import com.robinfood.repository.orderfinalproductportions.IOrderFinalProductPortionRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_FALSE_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_EMPTY_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

/**
 * Implementation of ICreateOrderFinalProductPortionUseCase
 */
@Component
@Slf4j
public class CreateOrderFinalProductPortionsUseCase implements
        ICreateOrderFinalProductPortionsUseCase {

    private final IOrderFinalProductPortionRepository orderFinalProductPortionRepository;
    private final ICreateOrderChangedPortionUseCase createOrderChangedPortionUseCase;

    public CreateOrderFinalProductPortionsUseCase(
            IOrderFinalProductPortionRepository orderFinalProductPortionRepository,
            ICreateOrderChangedPortionUseCase createOrderChangedPortionUseCase
    ) {
        this.orderFinalProductPortionRepository = orderFinalProductPortionRepository;
        this.createOrderChangedPortionUseCase = createOrderChangedPortionUseCase;
    }

    @Override
    public CompletableFuture<Boolean> invoke(List<FinalProductPortionDTO> portions) {

        log.info("Starting process to save order product portions with data: {}", portions);

        portions = this.addAddictionToPortions(portions);

        List<OrderFinalProductPortionEntity> orderFinalProductPortionEntities = CollectionsKt.map(
                portions,
                FinalProductPortionMappers::toOrderFinalProductPortionEntity
        );

        orderFinalProductPortionEntities = CollectionsKt.toList(
                this.orderFinalProductPortionRepository.saveAll(orderFinalProductPortionEntities)
        );

        final List<OrderFinalProductPortionEntity> orderFinalProductPortionChangedEntities = CollectionsKt.filter(
                orderFinalProductPortionEntities,
                OrderFinalProductPortionEntity::getChangedProduct
        );

        final List<FinalProductPortionDTO> portionsReplaced = CollectionsKt.filter(
                portions,
                portionDTO -> portionDTO.getReplacementPortion() != null
        );

        if (!portionsReplaced.isEmpty()) {

            for (int index = 0; index < orderFinalProductPortionChangedEntities.size(); index++) {
                portionsReplaced.get(index).setOrderFinalProductPortionId(
                        orderFinalProductPortionChangedEntities.get(index).getId()
                );
            }

            createOrderChangedPortionUseCase.invoke(portionsReplaced);
        }

        return CompletableFuture.completedFuture(
                orderFinalProductPortionEntities.size() == portions.size()
        );
    }

    /**
     * This function divides the portions when the free option is less than or equal to the quantity
     *
     * @param portionDTOS The list portions requested for each product
     * @return The list of portions divided by free or additional shares
     */
    private List<FinalProductPortionDTO> addAddictionToPortions(List<FinalProductPortionDTO> portionDTOS) {

        final List<FinalProductPortionDTO> finalProductPortionDTOS = new ArrayList<>();

        for (FinalProductPortionDTO finalProductPortionDTO : portionDTOS) {

            final int differenceBetweenFreeAndQuantity =
                    finalProductPortionDTO.getQuantity() - finalProductPortionDTO.getFree();

            if (differenceBetweenFreeAndQuantity < DEFAULT_DOUBLE_EMPTY_VALUE) {
                finalProductPortionDTO.setAddition(DEFAULT_BOOLEAN_FALSE_VALUE);
                finalProductPortionDTOS.add(finalProductPortionDTO);
                continue;
            }

            if (differenceBetweenFreeAndQuantity != DEFAULT_DOUBLE_EMPTY_VALUE) {

                if (finalProductPortionDTO.getFree() != DEFAULT_INTEGER_VALUE) {

                    final FinalProductPortionDTO finalProductPortionFree = new FinalProductPortionDTO()
                            .generateANewReference(finalProductPortionDTO);

                    finalProductPortionFree.setQuantity(
                            finalProductPortionDTO.getFree()
                    );

                    finalProductPortionDTOS.add(finalProductPortionFree);
                }

                finalProductPortionDTO.setReplacementPortion(null);
                finalProductPortionDTO.setAddition(DEFAULT_BOOLEAN_TRUE_VALUE);
                finalProductPortionDTO.setFree(DEFAULT_INTEGER_VALUE);
                finalProductPortionDTO.setQuantity(differenceBetweenFreeAndQuantity);

            } else {
                finalProductPortionDTO.setAddition(DEFAULT_BOOLEAN_FALSE_VALUE);
            }

            finalProductPortionDTOS.add(finalProductPortionDTO);
        }

        return finalProductPortionDTOS;
    }
}
