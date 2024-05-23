package com.robinfood.network.api;

import com.robinfood.core.entities.APIServicesResponseEntity;
import com.robinfood.core.entities.services.ServiceDetailEntity;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Defines all connections to Services Business Capability
 */
@Component
public interface ServiceBcAPI {

    /**
     * Connects to an endpoint and returns the service detail
     *
     * @param orderUId order uid
     * @param token    the authorization token
     * @return the service detail
     */
    @GET("v1/services/{orderUId}")
    Call<APIServicesResponseEntity<ServiceDetailEntity>> getServiceDetail(
        @Header("Authorization") String token,
        @Path("orderUId") String orderUId
    );

}
