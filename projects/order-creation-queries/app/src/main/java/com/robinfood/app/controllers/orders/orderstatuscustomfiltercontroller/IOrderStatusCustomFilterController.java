package com.robinfood.app.controllers.orders.orderstatuscustomfiltercontroller;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.StatusCustomFilterDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Controller that exposes final point in relation to the list status filter.
 */
public interface IOrderStatusCustomFilterController {

    /**
     * list status filter
     *
     * @return Return list status filter
     */
    ResponseEntity<ApiResponseDTO<List<StatusCustomFilterDTO>>> invoke();
}
