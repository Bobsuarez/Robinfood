package com.robinfood.app.controllers.users;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDetailDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import com.robinfood.core.exceptions.UnauthorizedAccessException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Exposes the API that handles all data related to users
 */
@Tag(name = "Users", description = "Requests for users related data")
public interface IUserController {

    /**
     * Retrieves the order history based on the following query params and user id
     *
     * @param currentPage the page the client needs of the current pagination
     * @param perPage     the number of results per page
     * @param token       the authentication token
     * @param userId      user id
     * @return the order history containing the orders detailed info
     */
    ResponseEntity<ApiResponseDTO<EntityDTO<ResponseOrderDTO>>> getOrderHistory(
        Integer currentPage,
        Integer perPage,
        String token,
        Long userId
    ) throws UnauthorizedAccessException;

    /**
     * Retrieves the order detail based on user id
     *
     * @param orderUId order uid
     * @param token    the authentication token
     * @param userId   user id
     * @return the order detailed info
     */
    ResponseEntity<ApiResponseDTO<ResponseOrderDetailDTO>> getOrderDetail(
        String orderUId,
        String token,
        Long userId
    ) throws UnauthorizedAccessException;

    /**
     * Retrieves the active order based on the following by user id
     *
     * @param token  the authentication token
     * @param userId user id
     * @return the active orders containing the orders detailed info
     */
    ResponseEntity<ApiResponseDTO<List<ResponseOrderDTO>>> getActiveOrders(
        String token,
        Long userId
    ) throws UnauthorizedAccessException;

}
