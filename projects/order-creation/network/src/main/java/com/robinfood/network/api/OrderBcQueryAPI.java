package com.robinfood.network.api;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.InformationPosResolutionsResponseEntity;
import org.springframework.data.repository.query.Param;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.time.LocalDate;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_KEY;
import static com.robinfood.core.constants.APIConstants.LOCAL_DATE_END_PARAM;
import static com.robinfood.core.constants.APIConstants.LOCAL_DATE_START_PARAM;
import static com.robinfood.core.constants.APIConstants.TIMEZONE_HEADER;

/**
 * Connections to Order Pos Resolution Business Capability
 */
public interface OrderBcQueryAPI {

    /**
     * Get the information of Resolutions by PosId
     *
     * @param token the token to retrieve the information
     * @param timeZone the time zone to retrieve the information
     * @param posId the id pos for filter the information
     * @param localDateEnd the local date end to retrieve the information
     * @param localDateStart the local date start to retrieve the information
     * @return the information of Resolutions by PosId
     */
    @GET("v1/orders/pos-resolutions/{posId}/sequence")
    Call<ApiResponseEntity<InformationPosResolutionsResponseEntity>> getResolutionByPos(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Header(TIMEZONE_HEADER) String timeZone,
            @Path("posId") Long posId,
            @Query(LOCAL_DATE_END_PARAM) LocalDate localDateEnd,
            @Query(LOCAL_DATE_START_PARAM) LocalDate localDateStart
    );
}
