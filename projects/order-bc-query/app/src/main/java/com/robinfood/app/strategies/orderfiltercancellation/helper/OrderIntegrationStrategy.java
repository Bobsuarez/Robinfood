package com.robinfood.app.strategies.orderfiltercancellation.helper;

import com.robinfood.app.mappers.SearchToCriteriaMapper;
import com.robinfood.app.strategies.querystatements.context.SearchQueryStrategyContext;
import com.robinfood.app.strategies.querystatements.helper.JoinOrderIntegrationPredicateStrategy;

import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDER_INTEGRATION;

public class OrderIntegrationStrategy<T> implements IFilterCustomStrategy<T> {

    @Override
    public void buildFilter(SearchQueryStrategyContext<T> searchQueryStrategyContext, String value) {

        searchQueryStrategyContext.add(SearchToCriteriaMapper
                .buildDataSearch(ORDER_INTEGRATION, value), value, new JoinOrderIntegrationPredicateStrategy<>());
    }
}
