package com.robinfood.app.usecases.getorderhistoryitems;

import com.robinfood.core.dtos.OrderHistoryItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

/**
 * Use case that returns all order items that are contained in the order history
 */
public interface IGetOrderHistoryItemsUseCase {

    /**
     * Returns a page that contains all the pagination information for the current page
     * @param createdAt the date used to filter the history
     * @param deliveryTypeIds the ids of delivery types used to filter the order list
     * @param originId the id of the origin where the order was created
     * @param pageRequest the information of the page the client needs
     * @param isPaid true if the orders are paid, false otherwise
     * @param storeId the if of the store where the order was created
     * @return a page that contains all the pagination information for the current page
     */
    Page<OrderHistoryItemDTO> invoke(
            LocalDate createdAt,
            List<Long> deliveryTypeIds,
            Long originId,
            PageRequest pageRequest,
            Boolean isPaid,
            Long storeId
    );
}
