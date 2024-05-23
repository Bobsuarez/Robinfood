package com.robinfood.app.strategies.orderfiltercancellation.context;

import com.robinfood.app.strategies.querystatements.context.SearchQueryStrategyContext;
import com.robinfood.app.strategies.orderfiltercancellation.helper.IFilterCustomStrategy;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class ProcessFilterContext<T> {

    private IFilterCustomStrategy<T> filterCustom;

    public void processFilter(
            Long idCustom,
            Long codeOperation,
            SearchQueryStrategyContext<T> searchQueryStrategyContext,
            String value
    ) {
        if (Optional.ofNullable(idCustom).equals(Optional.ofNullable(codeOperation))) {
            filterCustom.buildFilter(searchQueryStrategyContext, value);
        }
    }
}
