package com.robinfood.app.strategies.orderfiltercancellation.helper;

import com.robinfood.app.mappers.SearchToCriteriaMapper;
import com.robinfood.app.strategies.querystatements.context.SearchQueryStrategyContext;
import com.robinfood.app.strategies.querystatements.helper.EqualPredicateStrategy;

import static com.robinfood.core.constants.OrderFilterColumnsConstants.ORDERS_INVOICE_NUMBER;

public class InvoiceNumberStrategy<T> implements IFilterCustomStrategy<T> {

    @Override
    public void buildFilter(SearchQueryStrategyContext<T> searchQueryStrategyContext, String value) {

        searchQueryStrategyContext.add(SearchToCriteriaMapper
                .buildDataSearch(ORDERS_INVOICE_NUMBER, value), value, new EqualPredicateStrategy<>());
    }
}
