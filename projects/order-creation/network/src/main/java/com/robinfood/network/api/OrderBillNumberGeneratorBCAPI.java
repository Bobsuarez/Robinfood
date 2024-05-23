package com.robinfood.network.api;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.ApiResponseEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

import static com.robinfood.core.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

/**
 * Connections to Order Bill Number Generator Business Capability
 */
public interface OrderBillNumberGeneratorBCAPI {

    /**
     * API for Order Bill Number Generator
     * @param token                        the token for auth
     * @param transactionRequestDTO the order
     * @return ApiResponseEntity<> the response of order bill number generator bc
     */
    @POST("v1/send-electronic-bill")
    Call<ApiResponseEntity> sendElectronicBill(
            @Header(AUTHORIZATION_HEADER_KEY) String token,
            @Body TransactionRequestDTO transactionRequestDTO
    );
}
