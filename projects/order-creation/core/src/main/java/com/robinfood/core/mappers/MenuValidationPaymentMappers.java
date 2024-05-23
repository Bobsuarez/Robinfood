package com.robinfood.core.mappers;

import static com.robinfood.core.constants.GlobalConstants.TAX_TYPE;

import com.robinfood.core.dtos.transactionrequestdto.DiscountDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.entities.menuvalidationentities.MenuValidationPaymentEntity;
import java.math.BigDecimal;

public final class MenuValidationPaymentMappers {

    private MenuValidationPaymentMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static MenuValidationPaymentEntity toMenuValidationPaymentEntity(OrderDTO order) {
        return new MenuValidationPaymentEntity(
                order.getDiscounts().stream().map(DiscountDTO::getValue).reduce(BigDecimal.ZERO, BigDecimal::add),
                order.getTotal(),
                BigDecimal.ZERO,
                TAX_TYPE,
                order.getTotal()
        );
    }
}
