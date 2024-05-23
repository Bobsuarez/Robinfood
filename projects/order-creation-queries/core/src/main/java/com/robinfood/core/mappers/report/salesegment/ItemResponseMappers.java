package com.robinfood.core.mappers.report.salesegment;

import com.robinfood.core.dtos.report.salebysegment.response.ItemDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.OrdersDTOResponse;

public final class ItemResponseMappers {

    private ItemResponseMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static ItemDTOResponse itemResponseMappers(
            String currency,
            Long itemId,
            String nameItem,
            OrdersDTOResponse orders
    ) {
        return ItemDTOResponse.builder()
                .currency(currency)
                .id(itemId)
                .name(nameItem)
                .orders(orders)
                .build();
    }
}
