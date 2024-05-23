package com.robinfood.repository.services;

import static com.robinfood.core.extensions.NetworkExtensionsKt.safeAPICall;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.servicesentities.ServiceListRequestEntity;
import com.robinfood.core.entities.servicesentities.ServiceValidationResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.network.api.ServiceBCApi;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class ServicesDataSource implements IServicesDataSource {

    private final ServiceBCApi serviceBCApi;

    public ServicesDataSource(ServiceBCApi serviceBCApi) {
        this.serviceBCApi = serviceBCApi;
    }

    @Override
    public CompletableFuture<Boolean> validateService(String token,
            ServiceListRequestEntity requestEntity
    ) {

        Result<ApiResponseEntity<ServiceValidationResponseEntity>> serviceResult =
                safeAPICall(serviceBCApi.validateServices(token, requestEntity));

        if (serviceResult instanceof Result.Error) {
            final Result.Error error = (Result.Error) serviceResult;

            throw new TransactionCreationException(
                    error.component1().getMessage(),
                    "Error validate service",
                    TransactionCreationErrors.INVALID_ORDER_SERVICE,
                    HttpStatus.PRECONDITION_FAILED
            );
        }

        return CompletableFuture.completedFuture(
                serviceResult instanceof Result.Success
        );
    }
}
