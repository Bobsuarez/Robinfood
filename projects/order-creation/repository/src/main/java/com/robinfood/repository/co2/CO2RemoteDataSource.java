package com.robinfood.repository.co2;


import static com.robinfood.core.extensions.NetworkExtensionsKt.safeAPICall;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.CO2CalculateRequestEntity;
import com.robinfood.core.entities.CO2CalculateResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.network.api.CO2BCApi;
import org.springframework.http.HttpStatus;

/**
 * Implementation of ICO2RemoteDataSource
 */
public class CO2RemoteDataSource implements ICO2RemoteDataSource {

    private final CO2BCApi co2BCAPI;

    public CO2RemoteDataSource(CO2BCApi co2BCAPI) {
        this.co2BCAPI = co2BCAPI;
    }

    @Override
    public CO2CalculateResponseEntity calculateCO2Compensation(
            String token,
            CO2CalculateRequestEntity co2CalculateRequestEntity
    ) {
        Result<ApiResponseEntity<CO2CalculateResponseEntity>> co2Calculate =
                safeAPICall(co2BCAPI.co2Calculate(token, co2CalculateRequestEntity));

        if (co2Calculate instanceof Result.Error) {
            final Result.Error error = (Result.Error) co2Calculate;

            throw new TransactionCreationException(
                    error.component1().getMessage(),
                    "Error validating co2 compensation from co2-bc",
                    TransactionCreationErrors.CO2_BC_ERROR,
                    HttpStatus.PRECONDITION_FAILED
            );
        }

        return ((Result.Success<ApiResponseEntity<CO2CalculateResponseEntity>>) co2Calculate).getData()
                .getData();
    }

}
