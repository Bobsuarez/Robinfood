package com.robinfood.app.strategies.orderfiltercancellation.helper;

import com.robinfood.app.mappers.SearchToCriteriaMapper;
import com.robinfood.app.strategies.querystatements.context.SearchQueryStrategyContext;
import com.robinfood.app.strategies.querystatements.helper.LikePredicateStrategy;

import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_ORDER_NUMBER;

public class OrderNumberStrategy<T> implements IFilterCustomStrategy<T> {

    @Override
    public void buildFilter(SearchQueryStrategyContext<T> searchQueryStrategyContext, String value) {

        searchQueryStrategyContext.add(SearchToCriteriaMapper
                .buildDataSearch(ORDERS_ORDER_NUMBER, value), value, new LikePredicateStrategy<>());
    }
}
