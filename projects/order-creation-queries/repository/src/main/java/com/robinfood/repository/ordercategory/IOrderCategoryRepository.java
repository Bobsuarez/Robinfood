package com.robinfood.repository.ordercategory;

import com.robinfood.core.dtos.ordercategories.DataRequestOrderCategoryDTO;
import com.robinfood.core.dtos.ordercategories.OrderCategoryDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * repository the list of orders grouped by categories
 */
public interface IOrderCategoryRepository {

    /**
     * Get list categories of orders
     *
     * @param dataRequestOrderCategoryDTO information for the different filters
     * @param token                       token auth service
     * @return the order list with the info categories
     */
    Result<List<OrderCategoryDTO>> getOrderListCategories(
            DataRequestOrderCategoryDTO dataRequestOrderCategoryDTO,
            String token
    );
}
