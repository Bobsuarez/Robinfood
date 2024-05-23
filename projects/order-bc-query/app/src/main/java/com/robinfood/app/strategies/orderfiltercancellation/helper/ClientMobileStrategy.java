package com.robinfood.app.strategies.orderfiltercancellation.helper;

import com.robinfood.app.mappers.SearchToCriteriaMapper;
import com.robinfood.app.strategies.querystatements.context.SearchQueryStrategyContext;
import com.robinfood.app.strategies.querystatements.helper.JoinOrderUserDataPredicateStrategy;

import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDER_USER_DATA_MOBILE;

public class ClientMobileStrategy<T> implements IFilterCustomStrategy<T> {

    @Override
    public void buildFilter(SearchQueryStrategyContext<T> searchQueryStrategyContext, String value) {

        searchQueryStrategyContext.add(SearchToCriteriaMapper
                .buildDataSearch(ORDER_USER_DATA_MOBILE, value), value, new JoinOrderUserDataPredicateStrategy<>());
    }
}
