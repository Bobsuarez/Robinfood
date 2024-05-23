package com.robinfood.repository.salesstore;

import com.robinfood.core.dtos.salesstore.SalesStoresDTO;
import com.robinfood.core.enums.Result;

import java.time.LocalDateTime;

/**
 * Repository for sales by store group by payment methods invoice data
 */
public interface ISalesStoreRepository {

    /**
     * Get object with sales by store group by payment methods
     *
     * @param dateTimeCurrent date to consult the records.
     * @param storeId id the store to search
     * @param token token auth service
     * @param timezone timezone of store
     * @return data sales stores group by payment methods
     */
    Result<SalesStoresDTO> getSalesByStoreGroupByPaymentMethods(
            LocalDateTime dateTimeCurrent,
            Long storeId,
            String timezone,
            String token
    );
}
