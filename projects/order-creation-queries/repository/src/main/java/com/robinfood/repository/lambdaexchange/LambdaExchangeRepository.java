package com.robinfood.repository.lambdaexchange;

import com.google.gson.JsonObject;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.NetworkExtensionsKt;
import com.robinfood.network.api.LambdaExchangesBcAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class LambdaExchangeRepository implements  ILambdaExchangeRepository{

    private final LambdaExchangesBcAPI lambdaExchangesBcAPI;

    public LambdaExchangeRepository (
            LambdaExchangesBcAPI lambdaExchangesBcAPI
    ) {
        this.lambdaExchangesBcAPI = lambdaExchangesBcAPI;
    }

    @Override
    public Result<JsonObject> getExchanges(String currentDate, String previousDate, String token) {

        log.info("Invoke get rest lambda  {}: {}", currentDate, previousDate);

        final Result<APIResponseEntity<JsonObject>> result = NetworkExtensionsKt.safeAPICall(
                lambdaExchangesBcAPI.getExchanges(
                        currentDate,
                        previousDate,
                        token
                )
        );

        if (result instanceof Result.Error) {
            final Result.Error resultError = (Result.Error) result;
            return new Result.Error(resultError.getException(), resultError.getHttpStatus());
        }

        final Result.Success<APIResponseEntity<JsonObject>> data =
                ((Result.Success<APIResponseEntity<JsonObject>>) result);
        return new Result.Success(
                data.getData().getData()
        );
    }
}
