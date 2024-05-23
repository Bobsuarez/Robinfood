package com.robinfood.network.api;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.ValidateTaxRequestEntity;
import com.robinfood.core.entities.ValidateTaxResponseEntity;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Connections to Taxes Business Capability
 */
public interface TaxesBCAPI {

    /**
     * Retrieves the list of taxes for some final products
     * @param token the authorization token
     * @param validateTaxRequest the final products that will request their taxes
     * @return the final product taxes
     */
    @POST("v1/items/taxes")
    Call<ApiResponseEntity<List<ValidateTaxResponseEntity>>> getFinalProductsTaxes(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Body ValidateTaxRequestEntity validateTaxRequest
    );
}
