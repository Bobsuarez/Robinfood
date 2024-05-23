package com.robinfood.ordereports.network.api;

import com.robinfood.app.library.dto.Answer;
import com.robinfood.ordereports.dtos.orders.OrderDetailDTO;
import com.robinfood.ordereports.entities.ApiResponseEntity;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

@Component
public interface OrderDetailBcAPI {


    @GET("v1/users/detail/{transactionUuid}")
    Call<ApiResponseEntity<OrderDetailDTO>> getOrderDetail(
            @Path("transactionUuid") String transactionUuid,
            @Header("Authorization") String token
    );
}
