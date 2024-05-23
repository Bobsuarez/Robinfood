package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.dtos.PaginationDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.OrderEntity;

import java.util.List;

public final class ResponseOrderFilterEntityMock {

    public static EntityDTO<OrderEntity> getDataDefault() {

        return EntityDTO.<OrderEntity>builder()
                .items(List.of(OrderEntity.builder().build()))
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

    public static APIResponseEntity<EntityDTO<OrderEntity>> getAPIResponseEntity() {

        return new APIResponseEntity<>(
                200,
                ResponseOrderFilterEntityMock.getDataDefault(),
                "CO",
                "Order filter returned",
                "200"
        );
    }
}
