package com.robinfood.app.usecases.getdeliverytypes;

import com.robinfood.app.mappers.DeliveryTypeMappers;
import com.robinfood.core.dtos.DeliveryTypeDTO;
import com.robinfood.repository.deliverytype.IDeliveryTypeRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<DeliveryTypeDTO> invoke() {

        log.info("Starting process to get delivery types");

        return deliveryTypeDataSource.findAll()
                .stream()
                .map(DeliveryTypeMappers::toDeliveryTypeDTO)
                .collect(Collectors.toList());
    }

}
