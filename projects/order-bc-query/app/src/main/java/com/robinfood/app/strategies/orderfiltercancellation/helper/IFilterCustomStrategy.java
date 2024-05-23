package com.robinfood.app.strategies.orderfiltercancellation.helper;

import com.robinfood.app.strategies.querystatements.context.SearchQueryStrategyContext;

public interface IFilterCustomStrategy<T> {

    void buildFilter(SearchQueryStrategyContext<T> searchQueryStrategyContext, String value);
}
