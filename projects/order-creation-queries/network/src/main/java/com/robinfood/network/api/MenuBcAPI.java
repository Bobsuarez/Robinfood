package com.robinfood.network.api;

import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.MenuCurrentEntity;
import com.robinfood.core.entities.menu.MenuBrandEntity;
import com.robinfood.core.entities.menu.MenuHallProductResponseEntity;
import com.robinfood.core.entities.menu.MenuSuggestedPortionResponseEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * Defines all connections to Menu Business Capability
 */
public interface MenuBcAPI {

    /**
     * Connects to an endpoint in menu business capability to get menu brand store
     *
     * @param token the authentication token
     * @param storeId the store identifier
     * @return the list menu brand result
     */
    @GET("v1/stores/{id}/brands")
    Call<APIResponseEntity<List<MenuBrandEntity>>> getMenuBrandStore(
            @Header("Authorization") String token,
            @Path("id") Long storeId
    );

    /**
     * Connects to an endpoint in menu business capability to get menu current
     * @param brandId the brand to consult
     * @param countryId the country to consult
     * @param flowId the flow to consult
     * @param storeId the store to consult
     * @param token the authentication token
     * @return the menu get result
     */
    @GET("v1/menus/current")
    Call<APIResponseEntity<MenuCurrentEntity>> getMenuCurrent(
            @Query("brand_id") Long brandId,
            @Query("country_id") Long countryId,
            @Query("flow_id") Long flowId,
            @Query("store_id") Long storeId,
            @Header("Authorization") String token
    );

    /**
     * Connects to an endpoint and returns the product detail
     * @param token the authorization token
     * @param productId product identifier
     * @param countryId country identifier
     * @param brandId brand identifier
     * @param flowId flow identifier
     * @param storeId store identifier
     *
     * @return the product detail
     */
    @GET("v1/menu-hall-products/{id}")
    Call<APIResponseEntity<MenuHallProductResponseEntity>> getProductDetail(
            @Path("id") Long productId,
            @Query("brand_id") Long brandId,
            @Query("country_id") Long countryId,
            @Query("flow_id") Long flowId,
            @Query("store_id") Long storeId,
            @Header("Authorization") String token
    );

    /**
     * Connects to an endpoint and returns the suggested portions
     * of the portions list given
     *
     * @param portionIds list with the portions identifier identifier
     * @param token the authorization token
     * @return the suggested portions for portions given
     */
    @GET("v1/portions/changes")
    Call<APIResponseEntity<List<MenuSuggestedPortionResponseEntity>>> getSuggestedPortions(
            @Query("portion_ids") List<Long> portionIds,
            @Header("Authorization") String token
    );
}
