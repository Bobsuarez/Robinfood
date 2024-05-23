package com.robinfood.network.api;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.userentities.UserDetailEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Connections to User Business Capability
 */
public interface UserBCAPI {
    /**
     * Api for validate users
     *
     * @param token        token access
     * @param userId        customer id
     * @return <ApiResponseEntity<UserDetailEntity>>  the response of validation
     */
    @GET("v1/users/{userId}")
    Call<ApiResponseEntity<UserDetailEntity>> getUserDetails(
        @Header(AUTHORIZATION_HEADER_KEY) String token,
        @Path("userId") Long userId
    );
}
