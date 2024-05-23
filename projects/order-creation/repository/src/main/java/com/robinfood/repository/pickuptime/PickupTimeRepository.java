package com.robinfood.repository.pickuptime;

import static com.robinfood.core.extensions.NetworkExtensionsKt.safeAPICall;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.WriteInTransactionException;
import com.robinfood.core.mappers.PickupTimeMappers;
import com.robinfood.core.models.domain.pickuptime.PickupTime;
import com.robinfood.core.models.retrofit.pickuptime.PickupTimeResponse;
import com.robinfood.network.api.StoreConfigurationsAPI;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PickupTimeRepository implements IPickupTimeRepository {

    private final StoreConfigurationsAPI storeConfigurationsAPI;
    private final PickupTimeMappers pickupTimeMappers;

    public PickupTimeRepository(StoreConfigurationsAPI storeConfigurationsAPI, PickupTimeMappers pickupTimeMappers) {
        this.storeConfigurationsAPI = storeConfigurationsAPI;
        this.pickupTimeMappers = pickupTimeMappers;
    }

    @Override
    public PickupTime getByTransaction(
        String token,
        TransactionRequestDTO transactionRequestDTO
    ) {
        log.info("Going out to find the pickup-time for the transaction {}",
                objectToJson(transactionRequestDTO));

        var request = pickupTimeMappers.toRequest(transactionRequestDTO);

        var pickupTimeValidationResult = safeAPICall(
            storeConfigurationsAPI.getByTransaction(token, request)
        );

        if (pickupTimeValidationResult instanceof Result.Error) {
            final Result.Error error = (Result.Error) pickupTimeValidationResult;

            throw new WriteInTransactionException(
                HttpStatus.PRECONDITION_FAILED,
                "Error validating pickup time: ".concat(error.component1().getMessage()),
                TransactionCreationErrors.STORE_CONFIGURATION_PICKUP_TIME_BC_ERROR
            );
        }

        var result = ((Result.Success<ApiResponseEntity<List<PickupTimeResponse>>>) pickupTimeValidationResult)
            .getData();

        return pickupTimeMappers.toDomain(result.getData());
    }

}
