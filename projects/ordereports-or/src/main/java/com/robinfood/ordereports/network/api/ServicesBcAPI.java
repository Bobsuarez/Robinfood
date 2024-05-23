package com.robinfood.ordereports.network.api;

import com.robinfood.ordereports.dtos.orders.DeliveryCourierServiceDTO;
import com.robinfood.ordereports.entities.ApiResponseEntity;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

@Component
public interface ServicesBcAPI {

    @GET("v1/couriers/{transactionUuid}")
    Call<ApiResponseEntity<DeliveryCourierServiceDTO>> getServices(
            @Path("transactionUuid") String transactionUuid,
            @Header("Authorization") String token
    );
}
