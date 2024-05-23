package com.robinfood.app.controllers.orders;

import com.robinfood.core.dtos.GetOrderDetailDTO;
import com.robinfood.core.dtos.GetOrderTotalDailySalesDTO;
import com.robinfood.core.dtos.HistoryDTO;
import com.robinfood.core.dtos.OrderHistoryItemDTO;
import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.dtos.response.ResponseCreateOrderTransactionDTO;
import com.robinfood.core.exceptions.CannotDivideByZeroException;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller that exposes different endpoints relative to orders
 */
public interface IOrderController {

    /**
     * Get state of an orden
     * @param idOrden of the order
     * @return Status of an order
     */
    ResponseEntity<ApiResponseDTO<OrderStateDTO>> getState(
            Long idOrden
    );

    /**
     * Get state parent
     * @param code of the order
     * @return Status parent
     */
    ResponseEntity<ApiResponseDTO<OrderStateDTO>> getStateParent(
            String code
    );

    /**
     * Create a transaction and orders
     * @param orderTransactionDTO contain the information of transaction and of the order list
     * @return the transaction of the created order
     */
    ResponseEntity<ApiResponseDTO<ResponseCreateOrderTransactionDTO>>
            createOrder(RequestOrderTransactionDTO orderTransactionDTO)
            throws CannotDivideByZeroException;

    /**
     * Retrieves the order history based on the following query params
     * @param timeZone time zone
     * @param createdAt the date to find history
     * @param currentPage the page the client needs of the current pagination
     * @param isPaid true if the returned orders are paid, false otherwise
     * @param isIntegration true if the order was made from one of the external delivery integrations
     *                      such as Rappi, UberEats, etc..., false otherwise
     * @param isInternalDelivery true if the order was made from one of the internal delivery services
     *                           such as Walkers, Mensajeros Urbanos, etc..., false otherwise
     * @param isOnPremise true if the order was made from one of the cloud restaurants, false otherwise
     * @param originId the order origin identifier
     * @param perPage the number of results per page
     * @param storeId the if of the store where the orders were created
     * @return the order history containing the orders detailed info
     */
    ResponseEntity<ApiResponseDTO<HistoryDTO<OrderHistoryItemDTO>>> getOrderHistory(
            String timeZone,
            LocalDate createdAt,
            Integer currentPage,
            Boolean isPaid,
            Boolean isIntegration,
            Boolean isInternalDelivery,
            Boolean isOnPremise,
            Long originId,
            Integer perPage,
            Long storeId
    );

    /**
     * Retrieves the order detail of multiple orders
     * The service does not respond at all, but the order id's are sent to them
     * @param orderIds the orders id
     * @param orderUids the orders uids
     * @return the order details containing the orders detailed info
     */
    ResponseEntity<ApiResponseDTO<List<GetOrderDetailDTO>>> getOrdersDetail(
            List<Long> orderIds,
            List<String> orderUids,
            List<String> orderUuid
    );

    /**
     * Retrieves the order detail of multiple orders for print ticket
     * The service does not respond at all, but the order id's are sent to them
     * @param orderIds the orders id
     * @param orderUids the orders uids
     * @return the order details containing the orders detailed info for print ticket
     */
    ResponseEntity<ApiResponseDTO<List<GetOrderDetailDTO>>> getOrdersDetailPrint(
            List<Long> orderIds,
            List<String> orderUids,
            List<String> orderUuid
    );
    
    /**
     * Retrieve the total daily sales by store and specific date
     * @author Marcos Manotas - CKS
     * @param storeId the store id to order
     * @param createdAt the create at to order
     * @return List<GetOrderTotalDailySalesDTO>
     */
    ResponseEntity<ApiResponseDTO<List<GetOrderTotalDailySalesDTO>>> getTotalDailySalesByParams(
        Long storeId,
        LocalDate createdAt);
}
