package com.robinfood.core.mappers;

import com.robinfood.core.dtos.transactionrequestdto.ServiceDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.servicesentities.ServiceListRequestEntity;
import com.robinfood.core.entities.servicesentities.ServiceRequestEntity;
import java.util.List;
import java.util.stream.Collectors;

public final class ServiceMappers {

    private ServiceMappers() {
    }

    public static ServiceRequestEntity serviceDTOToServiceEntity(ServiceDTO service,
            TransactionRequestDTO transactionRequest) {
        return ServiceRequestEntity.builder()
                .storeId(transactionRequest.getStoreInfo().getId())
                .grossValue(service.getValue())
                .discount(service.getDiscount())
                .netValue(service.getValue().subtract(service.getDiscount()))
                .build();
    }

    public static ServiceListRequestEntity serviceListRequestEntity(List<ServiceDTO> listService,
            TransactionRequestDTO transactionRequest) {
        List<ServiceRequestEntity> listEntities = listService
                .stream()
                .map(item -> serviceDTOToServiceEntity(item, transactionRequest))
                .collect(Collectors.toList());

        return ServiceListRequestEntity.builder()
                .stores(listEntities)
                .build();
    }
}
