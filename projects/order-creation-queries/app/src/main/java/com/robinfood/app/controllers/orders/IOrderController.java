package com.robinfood.app.controllers.orders;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.OrderDailySaleSummaryDTO;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.dtos.OrderTotalDailySalesDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 * Exposes the API that handles all data related to orders
 */
@Tag(name = "Orders", description = "Requests for orders related data")
public interface IOrderController {

    /**
     * Retrieves the order details based on the following query params
     * @param countryId country id
     * @param flowId flow id
     * @param orderIds the orders id
     * @param orderUids the orders uids
     * @param orderUuid the orders uuids
     * @return the order history containing the orders detailed info
     */
    ResponseEntity<ApiResponseDTO<List<OrderDetailDTO>>> getOrderDetails(
            Long countryId,
            Long flowId,
            List<Long> orderIds,
            List<String> orderUids,
            List<String> orderUuid
    );

    /**
     * Retrieves the order details based on the following query params
     * @param orderIds the orders id
     * @param orderUids the orders uids
     * @param orderUuid the orders uuids
     * @return the order history containing the orders detailed info
     */
    ResponseEntity<ApiResponseDTO<List<OrderDetailDTO>>> getOrderDetailPrint(
            List<Long> orderIds,
            List<String> orderUids,
            List<String> orderUuid
    );

    /**
     * Get daily sales summary by store and specific date
     *
     * @param storeId   the store id to order
     * @param createdAt the date of creation to order
     * @return an object of OrderDailySaleSummaryDTO
     * @author Jose Mario Londoño - CKS
     */
    ResponseEntity<ApiResponseDTO<OrderDailySaleSummaryDTO>> getDailySaleSummary(
        long storeId,
        LocalDate createdAt);

    /**
     * Get list orders with total daily sales
     * @param storeId id store
     * @param createdAt date consulting information
     * @return list orders total daily sales
     * @author Sebastian Pareja
     */
    ResponseEntity<ApiResponseDTO<List<OrderTotalDailySalesDTO>>> getTotalDailySales(
        int storeId,
        LocalDate createdAt
    );
}
