package com.robinfood.network.api;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.models.retrofit.menu.brand.BrandResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * APIs that connect to menu-base-admin-bc system
 */
public interface MenuBaseAdminBCAPI {

    /**
     * Connects to an endpoint in order to get brands in menu base admin business capability
     * @param token the authorization token
     * @param countryId to get brands
     * @return brand list
     */
    @GET("v1/brands")
    Call<ApiResponseEntity<List<BrandResponse>>> getBrandsByCountryId(
        @Header(AUTHORIZATION_HEADER_KEY) String token,
        @Query("country_id") Long countryId
    );

}
