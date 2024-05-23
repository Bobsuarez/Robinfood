package com.robinfood.repository.services;

import com.robinfood.core.dtos.transactionrequestdto.ServiceDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.servicesentities.ServiceListRequestEntity;
import com.robinfood.core.mappers.ServiceMappers;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServicesRepository implements IServicesRepository {

    private IServicesDataSource servicesDataSource;

    public ServicesRepository(IServicesDataSource servicesDataSource) {
        this.servicesDataSource = servicesDataSource;
    }

    @Override
    public CompletableFuture<Boolean> validateService(String token, List<ServiceDTO> servicesDTOList,
            TransactionRequestDTO transactionRequest) {

        ServiceListRequestEntity requestEntity = ServiceMappers.serviceListRequestEntity(servicesDTOList,
                transactionRequest);

        return servicesDataSource.validateService(token, requestEntity);
    }
}
