package com.robinfood.network.api;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

import com.robinfood.core.entities.ApiResponseEntity;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Connections to Deduction Business Capability
 */
public interface DeductionsApi {

    /**
     * Connects to an endpoint and checks if the user has applied consumption discount on a certain
     * date
     *
     * @param token the authorization date
     *
     * @return otherwise
     */
    @GET("v1/deductions")
    Call<ApiResponseEntity<Map<Long, String>>> getAllActiveTypeDeductions(
            @Header(AUTHORIZATION_HEADER_KEY) String token
    );
}
