package com.robinfood.app.usecases.getstoreorders;

import com.robinfood.core.dtos.storeorder.StoreOrderDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Get Store Orders Use Case
 */
public interface IGetStoreOrdersUseCase {

    /**
     * Retrieve the orders by store
     *
     * @param localDateEnd   End date to consult the records
     * @param localDateStart Start date to consult the records
     * @param storeId          identifier the store
     * @param timeZone       Geographic region
     * @return Object with the data the order by storeId
     */
    List<StoreOrderDTO> invoke(
            LocalDate localDateEnd,
            LocalDate localDateStart,
            Long storeId,
            String timeZone
    );

}
