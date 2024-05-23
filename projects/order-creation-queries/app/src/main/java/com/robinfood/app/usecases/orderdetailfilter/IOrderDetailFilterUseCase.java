package com.robinfood.app.usecases.orderdetailfilter;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.enums.Result;

import java.time.LocalDate;

/**
 * Use Case orders filters by store Id, orderNumber and orderInvoiceNumber.
 */
public interface IOrderDetailFilterUseCase {

    /**
     * Order detail filter order information by different fields
     *
     * @param currentPage Current Page of Records.
     * @param filterText Filter text for records by (orderNumber and orderInvoiceNumber).
     * @param localDateEnd End date to consult the records.
     * @param localDateStart Start date to consult the records.
     * @param perPage Records by pages.
     * @param storeId Number to filter by Id from the store.
     * @param timeZone Geographic region in which the same time is used.
     * @return Return order filter detail
     */
    Result<EntityDTO<OrderDTO>> invoke(
            Integer currentPage,
            String filterText,
            LocalDate localDateEnd,
            LocalDate localDateStart,
            Integer perPage,
            Long storeId,
            String timeZone
    );
}
