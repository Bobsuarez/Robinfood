package com.robinfood.network.api;

import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.loyalty.UserLoyaltyTierResponseEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Defines all connections to Loyalty Business Capability
 */
public interface LoyaltyBcAPI {

    /**
     * Connects to an endpoint and returns the user loyalty tier
     * @param userId the user identifier
     * @param token authorization token
     *
     * @return the user loyalty tier
     */
    @GET("v1/users/{userId}/tier")
    Call<APIResponseEntity<UserLoyaltyTierResponseEntity>> getUserLoyaltyTier(
            @Path("userId") Long userId,
            @Header("Authorization") String token
    );
}
