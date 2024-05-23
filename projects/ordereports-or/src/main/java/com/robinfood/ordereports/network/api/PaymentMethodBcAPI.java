package com.robinfood.ordereports.network.api;

import com.robinfood.app.library.dto.Answer;
import com.robinfood.ordereports.dtos.orders.PaymentDetailDTO;
import com.robinfood.ordereports.entities.ApiResponseEntity;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

@Component
public interface PaymentMethodBcAPI {

    @GET("v1/transactions/{transactionUuid}")
    Call<ApiResponseEntity<PaymentDetailDTO>> getPaymentMethod(
            @Path("transactionUuid") String transactionUuid,
            @Header("Authorization") String token
    );
}
