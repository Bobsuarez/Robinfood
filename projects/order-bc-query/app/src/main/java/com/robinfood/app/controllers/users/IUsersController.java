package com.robinfood.app.controllers.users;

import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.response.EntityDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDetailDTO;
import com.robinfood.core.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface IUsersController {

    /**
     * Checks if the user has applied the consumption discount to any order created at any date.
     * @param createdAt the date when the orders to check were created
     * @param userId the id of the user
     * @return true if the user has applied consumption discount to any order on a certain date,
     * false otherwise
     */
    ResponseEntity<ApiResponseDTO<Boolean>> hasUserAppliedConsumptionToday(
            LocalDate createdAt,
            Long userId
    );

    /**
     * Retrieves the order history by user
     * @param currentPage current page
     * @param perPage records per page
     * @param userId the id of the user
     * @return the order history containing the orders info
     */
    ResponseEntity<ApiResponseDTO<EntityDTO<ResponseOrderDTO>>> getOrderHistory(
        Integer currentPage,
        Integer perPage,
        Long userId
    );

    /**
     * Retrieves the order detail by orderUId
     * @param orderUId the order uid
     * @param userId the id of the user
     * @return the order detail containing the orders detailed info
     */
    ResponseEntity<ApiResponseDTO<ResponseOrderDetailDTO>> getOrderDetail(
        String orderUId,
        Long userId
    ) throws ResourceNotFoundException;

    /**
     * Retrieves the active orders by user
     * @param userId the id of the user
     * @return the active orders containing the orders info
     */
    ResponseEntity<ApiResponseDTO<List<ResponseOrderDTO>>> getActiveOrdersByUser(
        Long userId
    );
}
