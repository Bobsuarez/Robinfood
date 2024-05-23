package com.robinfood.network.api;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.servicesentities.ServiceListRequestEntity;
import com.robinfood.core.entities.servicesentities.ServiceValidationResponseEntity;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

/**
 * Connections to Services Validations
 */
@Component
public interface ServiceBCApi {

    /**
     * Connects to an endpoint to validate services of an order
     *
     * @param token              the authorization token
     * @param serviceListRequestEntity Request body with service to be validate
     * @return the response of the validation service
     */
    @POST("v1/shippings/costs/validate")
    Call<ApiResponseEntity<ServiceValidationResponseEntity>> validateServices(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Body ServiceListRequestEntity serviceListRequestEntity
    );

}
