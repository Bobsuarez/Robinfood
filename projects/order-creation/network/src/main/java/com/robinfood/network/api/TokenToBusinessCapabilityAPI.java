package com.robinfood.network.api;

import com.robinfood.core.models.retrofit.TokenRequest;
import com.robinfood.core.models.retrofit.TokenResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TokenToBusinessCapabilityAPI {
    /**
     * Retrieves a token
     * @param validateTaxRequest the authorization token
     * @return the final product taxes
     */
    @POST("v1/services")
    Call<TokenResponse> get(@Body TokenRequest validateTaxRequest);

}
