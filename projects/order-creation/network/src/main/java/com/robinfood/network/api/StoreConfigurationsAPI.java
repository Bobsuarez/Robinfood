package com.robinfood.network.api;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.models.retrofit.pickuptime.PickupTimeRequest;
import com.robinfood.core.models.retrofit.pickuptime.PickupTimeResponse;
import java.util.List;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Connections to Store Configurations Business Capability
 */
@Component
public interface StoreConfigurationsAPI {

    /**
     * Method that obtains the pickup-time from the store configurations
     *
     * @param token from SSO
     * @param request to get pickup time
     * @return response
     */
    @POST("v1/stores/brands-pickup-times")
    Call<ApiResponseEntity<List<PickupTimeResponse>>> getByTransaction(
        @Header(AUTHORIZATION_HEADER_KEY) String token,
        @Body PickupTimeRequest request);

}
