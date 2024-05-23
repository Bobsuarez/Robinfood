package com.robinfood.app.usecases.getreportsalesstores;

import com.robinfood.core.dtos.reportsalesstore.ReportSalesStoresDTO;
import com.robinfood.core.enums.Result;

import java.time.LocalDateTime;

/**
 * Use case that returns the sales by store group by payment methods
 */
public interface IGetReportSalesStoreGroupByPaymentMethodsUseCase {

    /**
     * retrieve the sales by store according to the entered criteria
     *
     * @param dateTimeCurrent date to consult the records
     * @param storeId id the store to search
     * @return object with the data sales store group by payment methods
     */
    Result<ReportSalesStoresDTO> invoke(LocalDateTime dateTimeCurrent, Long storeId);
}
