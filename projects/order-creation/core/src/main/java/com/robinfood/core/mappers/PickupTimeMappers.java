package com.robinfood.core.mappers;

import com.robinfood.core.dtos.transactionrequestdto.FinalProductBrandDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.dtos.transactionresponsedto.BrandResponseDTO;
import com.robinfood.core.dtos.transactionresponsedto.PickupTimeResponseDTO;
import com.robinfood.core.dtos.transactionresponsedto.StoreResponseDTO;
import com.robinfood.core.models.domain.pickuptime.Brand;
import com.robinfood.core.models.domain.pickuptime.PickupTime;
import com.robinfood.core.models.domain.pickuptime.Store;
import com.robinfood.core.models.retrofit.pickuptime.BrandRequest;
import com.robinfood.core.models.retrofit.pickuptime.BrandResponse;
import com.robinfood.core.models.retrofit.pickuptime.PickupTimeRequest;
import com.robinfood.core.models.retrofit.pickuptime.PickupTimeResponse;
import com.robinfood.core.models.retrofit.pickuptime.StoreRequest;
import com.robinfood.core.models.retrofit.pickuptime.StoreResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PickupTimeMappers {

    /**
     * Method that converts the DTO request into a retrofit model
     *
     * @param transactionRequestDTO request
     * @return valid model for pickuptime consumption
     */
    public PickupTimeRequest toRequest(TransactionRequestDTO transactionRequestDTO) {
        return PickupTimeRequest.builder()
            .time(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli())
            .flowId(transactionRequestDTO.getFlowId())
            .stores(toStoreRequestList(transactionRequestDTO.getOrders()))
            .build();
    }

    private List<StoreRequest> toStoreRequestList(List<OrderDTO> orderDTO) {
        return orderDTO.stream()
            .map(this::toStoreRequest)
            .collect(Collectors.toList());
    }

    private StoreRequest toStoreRequest(OrderDTO orderDTO) {
        return StoreRequest.builder()
            .id(orderDTO.getStore().getId())
            .brands(toBrandRequestList(orderDTO.getBrand()))
            .build();
    }

    private List<BrandRequest> toBrandRequestList(FinalProductBrandDTO brandDTO) {
        var brandRequest = BrandRequest.builder()
            .id(brandDTO.getId())
            .build();

        return Collections.singletonList(brandRequest);
    }

    /**
     * Method that converts from response retrofit to domain model
     *
     * @param responseList pickupTime response service
     * @return the domain model
     */
    public PickupTime toDomain(List<PickupTimeResponse> responseList) {
        List<Store> stores = new LinkedList<>();

        responseList
            .forEach(response -> stores.addAll(toStoreList(response.getStores())));

        return PickupTime.builder()
            .stores(stores)
            .build();
    }

    private List<Store> toStoreList(List<StoreResponse> storeResponsesList) {
        return storeResponsesList.stream()
            .map(this::toStore)
            .collect(Collectors.toList());
    }

    private Store toStore(StoreResponse storeResponse) {
        return Store.builder()
            .id(storeResponse.getId())
            .pickupTime(storeResponse.getPickupTime())
            .brands(toBrandList(storeResponse.getBrands()))
            .build();
    }

    private List<Brand> toBrandList(List<BrandResponse> brands) {
        return brands.stream()
            .map(this::toBrand)
            .collect(Collectors.toList());
    }

    private Brand toBrand(BrandResponse brandResponse) {
        return Brand.builder()
            .id(brandResponse.getId())
            .printTime(brandResponse.getPrintTime())
            .build();
    }

    /**
     * Method that converts from domain to response dto
     *
     * @param pickupTime
     * @return response dto
     */
    public PickupTimeResponseDTO toResponseDTO(PickupTime pickupTime) {
        return PickupTimeResponseDTO.builder()
            .stores(pickupTime.getStores().stream()
                .map(this::toStoreResponseDTO)
                .collect(Collectors.toList()))
            .build();
    }

    private StoreResponseDTO toStoreResponseDTO(Store store) {
        return StoreResponseDTO.builder()
            .id(store.getId())
            .pickupTime(
                Instant.ofEpochMilli(store.getPickupTime()).atZone(ZoneId.systemDefault())
                    .toLocalDateTime()
            )
            .brands(toBrandResponseDTOList(store.getBrands()))
            .build();
    }

    private List<BrandResponseDTO> toBrandResponseDTOList(List<Brand> brands) {
        return brands.stream()
            .map(this::toBrandResponseDTO)
            .collect(Collectors.toList());
    }

    private BrandResponseDTO toBrandResponseDTO(Brand brand) {
        return BrandResponseDTO.builder()
            .id(brand.getId())
            .printTime(
                Instant.ofEpochMilli(brand.getPrintTime()).atZone(ZoneId.systemDefault())
                    .toLocalDateTime()
            )
            .build();
    }
}
