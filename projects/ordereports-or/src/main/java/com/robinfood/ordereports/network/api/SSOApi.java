package com.robinfood.ordereports.network.api;


import com.robinfood.ordereports.models.retrofit.TokenRequest;
import com.robinfood.ordereports.models.retrofit.TokenResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Defines all connections to SSO capability
 */
public interface SSOApi {

    /**
     * Generate SSO Token
     *
     * @param tokenRequest --> Request body to SSO Token service
     * @return --> TokenResponse: Token generated
     */
    @POST("v1/services")
    Call<TokenResponse> get(@Body TokenRequest tokenRequest);

}
