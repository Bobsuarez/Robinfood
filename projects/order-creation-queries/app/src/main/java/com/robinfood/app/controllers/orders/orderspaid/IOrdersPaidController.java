package com.robinfood.app.controllers.orders.orderspaid;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.orderspaid.DataOrdersPaidRequestDTO;
import com.robinfood.core.dtos.orderspaid.OrdersPaidResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(
        name = "Get Orders Paid",
        description = "Allows you to obtain orders paid information"
)
public interface IOrdersPaidController {

    ResponseEntity<ApiResponseDTO<OrdersPaidResponseDTO>> invoke(
            DataOrdersPaidRequestDTO dataOrdersPaidRequestDTO,
            @RequestHeader String timeZone
    );
}
