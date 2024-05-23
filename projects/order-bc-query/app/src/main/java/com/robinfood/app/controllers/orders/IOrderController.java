package com.robinfood.app.controllers.orders;

import com.robinfood.core.dtos.GetOrderDetailDTO;
import com.robinfood.core.dtos.GetOrderPaymentMethodsDTO;
import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.dtos.TransactionCreateDTO;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.GetOrderTotalDailySalesDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_START;

/**
 * Controller that exposes different endpoints relative to orders
 */
public interface IOrderController {

    /**
     * Get state of an orden
     *
     * @param idOrden of the order
     * @return Status of an order
     */
    ResponseEntity<ApiResponseDTO<OrderStateDTO>> getState(
            Long idOrden
    );

    /**
     * Get state parent
     *
     * @param code of the order
     * @return Status parent
     */
    ResponseEntity<ApiResponseDTO<OrderStateDTO>> getStateParent(
            String code
    );

    /**
     * Retrieves the order detail of multiple orders
     * The service does not respond at all, but the order id's are sent to them
     *
     * @param orderIds  the orders id
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
     *
     * @param orderIds  the orders id
     * @param orderUids the orders uids
     * @return the order details containing the orders detailed info for print ticket
     */
    ResponseEntity<ApiResponseDTO<List<GetOrderDetailDTO>>> getOrdersDetailPrint(
            List<Long> orderIds,
            List<String> orderUids,
            List<String> orderUuid
    );

    /**
     * retrieves the grouped details of the payment methods
     *
     * @param posId          Filter by pos_id from the orders
     * @param localDateStart Start date to consult the records.
     * @param localDateEnd   End date to consult the records.
     * @param timeZone       Geographic region in which the same time is used.
     * @return payment method details list for voucher report
     */
    ResponseEntity<ApiResponseDTO<List<GetOrderPaymentMethodsDTO>>> getOrderPaymentMethods(
            @RequestParam(value = LOCAL_DATE_START)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDateStart,
            @RequestParam(value = LOCAL_DATE_END)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDateEnd,
            @PathVariable("posId") Long posId,
            @RequestHeader("TimeZone") String timeZone
    );

    /**
     * Retrieve the total daily sales by store and specific date
     *
     * @param storeId   the store id to order
     * @param createdAt the create at to order
     * @return List<GetOrderTotalDailySalesDTO>
     * @author Marcos Manotas - CKS
     */
    ResponseEntity<ApiResponseDTO<List<GetOrderTotalDailySalesDTO>>> getTotalDailySalesByParams(
            Long storeId,
            LocalDate createdAt);
}
