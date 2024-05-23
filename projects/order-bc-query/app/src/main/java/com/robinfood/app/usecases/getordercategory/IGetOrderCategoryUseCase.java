package com.robinfood.app.usecases.getordercategory;

import com.robinfood.core.dtos.ordercategories.OrderCategoryDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Use case that returns the information of the orders grouped by categories
 */
public interface IGetOrderCategoryUseCase {

    /**
     * Retrieve the orders according to the entered criteria
     *
     * @param localDateStart Start date to consult the records
     * @param localDateEnd   End date to consult the records
     * @param posId          identifier the pos
     * @param timeZone       Geographic region
     * @return Object with the data the order by categories
     */
    List<OrderCategoryDTO> invoke(
            LocalDate localDateStart,
            LocalDate localDateEnd,
            Long posId,
            String timeZone
    );
}
