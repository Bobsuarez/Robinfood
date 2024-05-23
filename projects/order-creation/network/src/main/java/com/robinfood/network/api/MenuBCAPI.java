package com.robinfood.network.api;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_KEY;
import static com.robinfood.core.constants.APIConstants.TIMEZONE_HEADER;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.menuvalidationentities.MenuValidationEntity;
import com.robinfood.core.models.retrofit.menu.hallproductdetail.MenuHallProductDetailResponse;
import com.robinfood.core.models.retrofit.menu.validate.DiscountProductsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * APIs that connect to menu-bc system
 */
public interface MenuBCAPI {

    /**
     * Connects to an endpoint in order to validate menu in menu business capability
     * @param token     the authorization token
     * @param timezone  store timezone
     * @param menu      the current order menu to validate
     * @return endpoint response
     */
    @POST("v1/orders/validate")
    Call<ApiResponseEntity<DiscountProductsResponse>> validateMenu(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Header(TIMEZONE_HEADER) String timezone,
            @Body MenuValidationEntity menu
    );

    /**
     * Connects to an endpoint in order to validate menu in menu business capability
     * @param token             the authorization token
     * @param menuHallProductId menu hall product id
     * @return endpoint response
     */
    @GET("v1/menu-hall-products/{id}/details")
    Call<ApiResponseEntity<MenuHallProductDetailResponse>> menuProductDetail(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Path("id") Long menuHallProductId
    );

}
