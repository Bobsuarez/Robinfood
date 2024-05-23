package com.robinfood.core.mappers;

import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.PortionDTO;
import com.robinfood.core.dtos.transactionrequestdto.RemovedPortionDTO;
import com.robinfood.core.entities.CO2CalculateRequestEntity;
import com.robinfood.core.entities.co2.CO2PortionEntity;
import com.robinfood.core.entities.co2.CO2RemovedPortionEntity;
import java.util.stream.Collectors;

public final class CO2CompensationMappers {

    private CO2CompensationMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static CO2CalculateRequestEntity toCO2CalculateRequestEntity(
        OrderDTO orderDTO
    ) {
        return CO2CalculateRequestEntity
            .builder()
            .products(
                orderDTO
                    .getFinalProducts()
                    .stream()
                    .map(CO2CompensationMappers::getCO2ProductEntity)
                    .collect(Collectors.toList())
            )
            .build();
    }

    private static CO2PortionEntity getCO2PortionEntity(PortionDTO portionDTO) {
        return CO2PortionEntity
            .builder()
            .id(portionDTO.getId())
            .sku(portionDTO.getSku())
            .quantity(portionDTO.getQuantity())
            .build();
    }

    private static CO2RemovedPortionEntity getCO2RemovedPortionEntity(
        RemovedPortionDTO removedPortionDTO
    ) {
        return CO2RemovedPortionEntity
            .builder()
            .id(removedPortionDTO.getId())
            .build();
    }

    private static CO2CalculateRequestEntity.Product getCO2ProductEntity(
        FinalProductDTO finalProductDTO
    ) {
        return CO2CalculateRequestEntity
                .Product.builder()
                .id(finalProductDTO.getId())
                .sku(finalProductDTO.getSku())
                .quantity(finalProductDTO.getQuantity())
                .portions(
                        finalProductDTO
                                .getGroups()
                                .stream()
                                .flatMap(
                                        groupDTO ->
                                                groupDTO.getPortions().stream()
                                )
                                .map(
                                        CO2CompensationMappers::getCO2PortionEntity
                                )
                                .collect(Collectors.toList())
                )
                .removedPortions(
                        finalProductDTO
                                .getRemovedPortions()
                                .stream()
                                .map(
                                        CO2CompensationMappers::getCO2RemovedPortionEntity
                                )
                                .collect(Collectors.toList())
                )
                .build();
    }
}
