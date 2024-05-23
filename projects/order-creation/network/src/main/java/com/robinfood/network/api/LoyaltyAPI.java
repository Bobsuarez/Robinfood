package com.robinfood.network.api;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.RedeemFoodCoinsRequestEntity;
import com.robinfood.core.entities.RedeemFoodCoinsResponseEntity;
import com.robinfood.core.entities.ValidateFoodCoinsResponseEntity;
import java.math.BigDecimal;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Connections to Loyalty Business Capability
 */
public interface LoyaltyAPI {

    /**
     * API for redeem or rollback the foodcoins
     *
     * @param token                        the token for auth
     * @param redeemFoodCoinsRequestEntity the redeem or rollback foodcoin request entity
     * @return ApiResponseEntity<RedeemFoodCoinsResponseEntity> the response of redeem or rollback
     * foodcoin
     */
    @POST("v1/transactions")
    Call<ApiResponseEntity<RedeemFoodCoinsResponseEntity>> redeemOrRollbackFoodCoins(
        @Header(AUTHORIZATION_HEADER_KEY) String token,
        @Body RedeemFoodCoinsRequestEntity redeemFoodCoinsRequestEntity
    );


    /**
     * Api for validate foodcoins
     *
     * @param token         the token for auth
     * @param amount        amount of foodcoins
     * @param countryId     id of the country of origin
     * @param operationType type of operation
     * @param userId        customer id
     * @return <ApiResponseEntity<ValidateFoodCoinsResponseEntity>>  the response of validation
     */
    @GET("v1/transactions/credits/validation")
    Call<ApiResponseEntity<ValidateFoodCoinsResponseEntity>> validateFoodcoins(
        @Header(AUTHORIZATION_HEADER_KEY) String token,
        @Query("amount") BigDecimal amount,
        @Query("country_id") Long countryId,
        @Query("operation_type") int operationType,
        @Query("user_id") Long userId
    );
}
