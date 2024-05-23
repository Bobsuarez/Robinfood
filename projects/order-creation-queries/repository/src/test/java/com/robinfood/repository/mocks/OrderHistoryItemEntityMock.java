package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.PaginationDTO;
import com.robinfood.core.entities.orderhistory.response.OrderHistoryItemEntity;

import java.util.List;

public final class OrderHistoryItemEntityMock {

    public static EntityDTO<OrderHistoryItemEntity> getDataDefault() {

        return EntityDTO.<OrderHistoryItemEntity>builder()
                .items(List.of(OrderHistoryItemEntity.builder().build()))
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
