package com.robinfood.app.usecases.getorderhistory;

import com.robinfood.core.dtos.HistoryDTO;
import com.robinfood.core.dtos.OrderHistoryItemDTO;

import java.time.LocalDate;

/**
 * Use case that gets the order history
 */
public interface IGetOrderHistoryUseCase {

    /**
     * Retrieves the order history based on the following query params
     * @param timeZoneCode time zone
     * @param createdAt the date to filter history
     * @param currentPage the page the client needs of the current pagination
     * @param isPaid true if the returned orders are paid, false otherwise
     * @param isIntegration true if the order was made from one of the external delivery integrations
     *                      such as Rappi, UberEats, etc..., false otherwise
     * @param isInternalDelivery true if the order was made from one of the internal delivery services
     *      *                      such as Walkers, Mensajeros Urbanos, etc..., false otherwise
     * @param isOnPremise true if the order was made from one of the cloud restaurants, false otherwise
     * @param originId the order origin identifier
     * @param perPage the number of results per page
     * @param storeId the if of the store where the orders were created
     * @return the order history containing the orders detailed info
     */
    HistoryDTO<OrderHistoryItemDTO> invoke(
            String timeZoneCode,
            LocalDate createdAt,
            Integer currentPage,
            Boolean isIntegration,
            Boolean isInternalDelivery,
            Boolean isOnPremise,
            Long originId,
            Boolean isPaid,
            Integer perPage,
            Long storeId
    );
}
