package com.robinfood.app.mappers;

import com.robinfood.core.dtos.pickuptime.BrandDTO;
import com.robinfood.core.dtos.pickuptime.PickupTimeDTO;
import com.robinfood.core.dtos.pickuptime.PickupTimeResponseDTO;
import com.robinfood.core.dtos.pickuptime.StoreDTO;
import com.robinfood.core.entities.PickupTimeEntity;
import com.robinfood.core.models.domain.Brand;
import com.robinfood.core.models.domain.PickupTime;
import com.robinfood.core.models.domain.Store;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PickupTimeMappers {

    /**
     * Method that convert domain to entity
     */
    public List<PickupTimeEntity> domainToEntities(PickupTime pickupTime) {

        List<PickupTimeEntity> pickupTimeEntities = new LinkedList<>();

        pickupTime.getStores()
            .forEach(store -> {
                var entities = store.getBrands().stream()
                    .map(brand -> buildPickupTimeEntity(
                        pickupTime.getTransactionId(),
                        store,
                        brand))
                    .collect(Collectors.toList());

                pickupTimeEntities.addAll(entities);
            });

        return pickupTimeEntities;
    }

    private PickupTimeEntity buildPickupTimeEntity(
        Long transactionId,
        Store store,
        Brand brand
    ) {
        return PickupTimeEntity.builder()
            .brandId(brand.getId())
            .pickupTime(store.getPickuptime())
            .printTime(brand.getPrintTime())
            .storeId(store.getId())
            .transactionId(transactionId)
            .build();
    }

    /**
     * Method that convert entity to domain
     */
    public PickupTime entityToDomain(PickupTimeEntity entity) {
        return PickupTime.builder()
            .id(entity.getId())
            .build();
    }

    public PickupTime dtoToDomain(PickupTimeDTO pickupTimeDTO) {
        return PickupTime.builder()
            .stores(storeDtoListToStoreDomainList(pickupTimeDTO.getStores()))
            .transactionId(pickupTimeDTO.getTransactionId())
            .build();
    }

    private List<Store> storeDtoListToStoreDomainList(List<StoreDTO> storeDTOS) {
        return storeDTOS.stream()
            .map(storeDTO -> Store.builder()
                .id(storeDTO.getId())
                .pickuptime(storeDTO.getPickupTime())
                .brands(brandDtoListToBrandDomainList(storeDTO.getBrands()))
                .build())
            .collect(Collectors.toList());
    }

    private List<Brand> brandDtoListToBrandDomainList(List<BrandDTO> brandDTOList) {
        return brandDTOList.stream()
            .map(brandDTO -> Brand.builder()
                .id(brandDTO.getId())
                .printTime(brandDTO.getPrintTime())
                .build())
            .collect(Collectors.toList());
    }

    public List<PickupTimeResponseDTO> domainListToDtoList(List<PickupTime> pickupTimes) {
        return pickupTimes.stream()
            .map(pickupTime -> PickupTimeResponseDTO.builder()
                .id(pickupTime.getId())
                .build())
            .collect(Collectors.toList());
    }

}
