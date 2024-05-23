package com.robinfood.repository.services;

import com.robinfood.core.dtos.services.ServiceDetailDTO;
import com.robinfood.core.entities.APIServicesResponseEntity;
import com.robinfood.core.entities.services.ServiceDetailEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.core.mappers.ServiceMappers;
import com.robinfood.network.api.ServiceBcAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Implementation of IServicesRepository
 */
@Repository
@Slf4j
public class ServiceRepository implements IServiceRepository {

    private final ServiceBcAPI serviceBcAPI;

    public ServiceRepository(ServiceBcAPI serviceBcAPI) {
        this.serviceBcAPI = serviceBcAPI;
    }

    @Override
    public Result<ServiceDetailDTO> getServiceDetail(
            String orderUId,
            String token
    ) {
        final Result<APIServicesResponseEntity<ServiceDetailEntity>> result = NetworkExtensionsKt.safeAPICall(
                serviceBcAPI.getServiceDetail(
                        token,
                        orderUId
            )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            log.error("Error get service detail {}", resultError.getException().getMessage());
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIServicesResponseEntity<ServiceDetailEntity>> data =
            ((Result.Success<APIServicesResponseEntity<ServiceDetailEntity>>) result);

        return new Result.Success(
            ServiceMappers.toServiceDetailDTO(data.getData().getData())
        );
    }

}
