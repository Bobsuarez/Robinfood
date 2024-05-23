package com.robinfood.app.usecases.getordercategory;

import com.robinfood.core.dtos.ordercategories.DataRequestOrderCategoryDTO;
import com.robinfood.core.dtos.ordercategories.OrderCategoryDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Use case that returns the information of the orders grouped by categories
 */
public interface IGetOrderCategoryUseCase {

    /**
     * retrieve the orders according to the entered criteria
     *
     * @param dataRequestOrderCategoryDTO Dto that contains the data entered
     * @return object with the data the order by categories
     */
    Result<List<OrderCategoryDTO>> invoke(DataRequestOrderCategoryDTO dataRequestOrderCategoryDTO);
}
