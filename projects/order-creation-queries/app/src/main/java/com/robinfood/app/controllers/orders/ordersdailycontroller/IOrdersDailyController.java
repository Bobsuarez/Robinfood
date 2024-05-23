package com.robinfood.app.controllers.orders.ordersdailycontroller;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.OrderDailyDTO;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * Controller that exposes final point in relation to the orders of the day.
 */
public interface IOrdersDailyController {

    /**
     * Get daily Order List ready to invoice
     *
     * @param storeId Store identifier
     * @param timeZone Client time zone
     * @return Daily Order List ready to invoice
     */
    ResponseEntity<ApiResponseDTO<List<OrderDailyDTO>>> invoke(
            @PathVariable @Min(1) Long storeId,
            @RequestHeader @Length(min = 1, max = 60) String timeZone
    );
}
