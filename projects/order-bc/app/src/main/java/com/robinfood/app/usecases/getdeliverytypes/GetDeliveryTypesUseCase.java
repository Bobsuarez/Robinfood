package com.robinfood.app.usecases.getdeliverytypes;

import com.robinfood.app.mappers.DeliveryTypeMappers;
import com.robinfood.core.dtos.DeliveryTypeDTO;
import com.robinfood.repository.deliverytype.IDeliveryTypeRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of IGetDeliveryTypesUseCase
 */
@Component
@Slf4j
public class GetDeliveryTypesUseCase implements IGetDeliveryTypesUseCase {

    private final IDeliveryTypeRepository deliveryTypeDataSource;

    public GetDeliveryTypesUseCase(IDeliveryTypeRepository deliveryTypeDataSource) {
        this.deliveryTypeDataSource = deliveryTypeDataSource;
    }

    @Override
    public List<DeliveryTypeDTO> invoke(
            boolean isIntegration,
            boolean isInternalDelivery,
            boolean isOnPremise
    ) {
        log.info(
                "Starting process to get delivery types with isIntegration [{}], " +
                        "isInternalDelivery [{}] and isOnPremise [{}]",
                isIntegration, isInternalDelivery, isOnPremise
        );

        final List<DeliveryTypeDTO> deliveryTypeDTOS = CollectionsKt.map(
                deliveryTypeDataSource.findAll(),
                DeliveryTypeMappers::toDeliveryTypeDTO
        );

        if (isIntegration) {
            return CollectionsKt.filter(
                    deliveryTypeDTOS,
                    DeliveryTypeDTO::getIsIntegration
            );
        }

        if (isInternalDelivery) {
            return CollectionsKt.filter(
                    deliveryTypeDTOS,
                    DeliveryTypeDTO::getIsInternalDelivery
            );
        }

        if (isOnPremise) {
            return CollectionsKt.filter(
                    deliveryTypeDTOS,
                    DeliveryTypeDTO::getIsOnPremise
            );
        }

        return deliveryTypeDTOS;
    }
}
