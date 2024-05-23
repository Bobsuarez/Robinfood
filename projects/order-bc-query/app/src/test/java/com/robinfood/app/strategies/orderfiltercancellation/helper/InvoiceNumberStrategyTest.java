package com.robinfood.app.strategies.orderfiltercancellation.helper;

import com.robinfood.app.strategies.querystatements.context.SearchQueryStrategyContext;
import com.robinfood.core.constants.GlobalConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InvoiceNumberStrategyTest<T> {

    @Mock
    private SearchQueryStrategyContext<T> searchQueryStrategyContext;

    @InjectMocks
    private InvoiceNumberStrategy<T> tInvoiceNumberStrategy;

    @Test
    void testBuildFilter() {
        tInvoiceNumberStrategy.buildFilter(searchQueryStrategyContext, GlobalConstants.DEFAULT_STRING_VALUE);
        Assertions.assertNotNull(searchQueryStrategyContext);
    }
}
