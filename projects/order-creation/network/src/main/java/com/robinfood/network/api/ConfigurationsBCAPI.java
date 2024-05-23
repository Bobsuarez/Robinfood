package com.robinfood.network.api;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.models.domain.configuration.Channel;
import com.robinfood.core.models.domain.configuration.StoreInformation;
import com.robinfood.core.models.retrofit.configuration.PosResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Connections to Configurations Business Capability
 */
public interface ConfigurationsBCAPI {

    /**
     * Method that obtains the pos id of the store
     *
     * @param token to access business capability
     * @param storeId store id to filter
     * @param posTypeId pos type id to filter
     *
     * @return posId
     */
    @GET("v1/pos")
    Call<ApiResponseEntity<PosResponse>> getPosId(
        @Header(AUTHORIZATION_HEADER_KEY) String token,
        @Query("store_id") Long storeId,
        @Query("pos_type_id") Long posTypeId
    );

    /**
     * Method that obtains the store configuration
     *
     * @param token to access business capability
     * @param storeId id the store
     *
     * @return posId
     */
    @GET("v1/stores/{id}")
    Call<ApiResponseEntity<StoreInformation>> getStore(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Path("id") Long storeId
    );

    /**
     * Method that obtains the channel configuration
     *
     * @param token to access business capability
     * @param channelId id the channel
     *
     * @return channelById
     */
    @GET("v1/channels/{channelId}")
    Call<ApiResponseEntity<Channel>> getChannel(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Path("channelId") Long channelId
    );

}
