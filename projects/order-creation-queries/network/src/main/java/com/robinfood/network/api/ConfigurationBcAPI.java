package com.robinfood.network.api;

import com.robinfood.core.dtos.configuration.BrandsDTO;
import com.robinfood.core.dtos.configuration.CompaniesDTO;
import com.robinfood.core.dtos.configuration.ChannelsDTO;
import com.robinfood.core.dtos.configuration.StoresDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.SortEntity;
import com.robinfood.core.entities.configurations.StoreEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * Defines all connections to Configuration Business Capability
 */
public interface ConfigurationBcAPI {

    /**
     * Connects to an endpoint and returns the store configuration
     *
     * @param storeId the store identifier
     * @param token Token auth service
     * @return Result store
     */
    @GET("v1/stores/{storeId}")
    Call<APIResponseEntity<StoreEntity>> getStore(
            @Path("storeId") Long storeId,
            @Header("Authorization") String token
    );

    /**
     * Connects to an endpoint and returns the list companies
     *
     * @param token Token auth service
     * @param status get active or disabled a company
     * @return Result channels
     */
    @GET("v1/companies")
    Call<APIResponseEntity<CompaniesDTO>> getCompanies(
            @Header("Authorization") String token,
            @Query("status") Integer status
    );

    /**
     * Connects to an endpoint and returns the list channels
     *
     * @param sort the sort of page
     * @param token Token auth service
     * @return Result channels
     */
    @GET("v1/channels")
    Call<APIResponseEntity<ChannelsDTO>> getChannels(
            @Query("sort") SortEntity sort,
            @Header("Authorization") String token
    );

    /**
     * Connects to an endpoint and returns the list Store
     *ç
     * @param sort the sort of page
     * @param token Token auth service
     * @return Result store
     */
    @GET("v1/stores")
    Call<APIResponseEntity<StoresDTO>> getStores(
            @Query("sort") SortEntity sort,
            @Header("Authorization") String token
    );

    /**
     * Connects to an endpoint and returns the brands configuration
     *
     * @param token Token auth service
     * @return Result store
     */
    @GET("v1/brands")
    Call<APIResponseEntity<BrandsDTO>> getBrands(
            @Header("Authorization") String token
    );

    /**
     * Get timezones by company id
     *
     * @param token Token auth service
     * @param companyId Company Id
     * @return Result store
     */
    @GET("v1/countries/cities/time-zones")
    Call<APIResponseEntity<List<String>>> getTimezonesByCompanyId(
            @Header("Authorization") String token,
            @Query("company_country_id") Long companyId
    );
}
