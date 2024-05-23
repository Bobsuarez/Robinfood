package com.robinfood.network.api;

import com.robinfood.core.entities.CO2CalculateResponseEntity;
import com.robinfood.core.entities.CO2CalculateRequestEntity;
import com.robinfood.core.entities.ApiResponseEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

/**
 * Connections to CO2 Business Capability
 */
public interface CO2BCApi {

    /**
     * API for CO2 compensation
     *
     * @param token                        the token for auth
     * @param co2CalculateRequestEntity the order productos information
     * @return ApiResponseEntity<CO2CalculateResponseEntity> the response of co2 bc
     */
    @POST("v1/co2/calculate")
    Call<ApiResponseEntity<CO2CalculateResponseEntity>> co2Calculate(
        @Header(AUTHORIZATION_HEADER_KEY) String token,
        @Body CO2CalculateRequestEntity co2CalculateRequestEntity
    );
}
