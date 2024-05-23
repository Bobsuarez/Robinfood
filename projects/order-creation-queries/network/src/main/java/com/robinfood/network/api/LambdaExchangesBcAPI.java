package com.robinfood.network.api;

import com.google.gson.JsonObject;
import com.robinfood.core.entities.APIResponseEntity;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Defines all connections to Lambda Exchange
 */
@Component
public interface LambdaExchangesBcAPI {

    /**
     * Connects to an endpoint and Returns data exchanges
     *
     * @param currentDate Current date to consult the records.
     * @param previousDate Previous date to consult the records.
     * @param token token with authorization.
     * @return object data Exchanges
     */
    @GET("v1/exchangeRates/latest")
    Call<APIResponseEntity<JsonObject>> getExchanges(
            @Query("CurrentDate") String currentDate,
            @Query("PreviousDate") String previousDate,
            @Header("Authorization") String token
    );
}
