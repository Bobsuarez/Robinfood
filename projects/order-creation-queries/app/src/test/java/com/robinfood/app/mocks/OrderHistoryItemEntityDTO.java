package com.robinfood.app.mocks;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.PaginationDTO;
import com.robinfood.core.dtos.orderhistory.response.OrderHistoryItemDTO;

import java.util.List;

public final class OrderHistoryItemEntityDTO {

    public static EntityDTO<OrderHistoryItemDTO> getDataDefault() {

        return EntityDTO.<OrderHistoryItemDTO>builder()
                .items(List.of(OrderHistoryItemDTO.builder().build()))
                .pagination(
                        PaginationDTO.builder()
                                .perPage(10)
                                .total(1L)
                                .lastPage(1)
                                .page(1)
                                .build()
                )
                .build();
    }

}
