package com.robinfood.app.controllers.orders;

import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.changestatusordersrequestdto.ChangeStatusOrdersRequestDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Exposes the API that handles all data related to orders
 */
@Tag(name = "Orders", description = "Requests for orders related data")
public interface IOrderController {

    /**
     * Updates orders status
     *
     * @param changeStatusOrderRequestDTO the information of the orders whose status will change
     * @return Success message update
     */
    ResponseEntity<ApiResponseDTO<Boolean>> changeStatus(
            HttpServletRequest httpServletRequest,
            ChangeStatusOrdersRequestDTO changeStatusOrderRequestDTO
    );
}
