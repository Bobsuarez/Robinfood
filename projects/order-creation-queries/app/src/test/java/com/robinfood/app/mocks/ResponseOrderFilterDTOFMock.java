package com.robinfood.app.mocks;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.dtos.PaginationDTO;

import java.util.List;

public final class ResponseOrderFilterDTOFMock {

    public static EntityDTO<OrderDTO> getDataDefault() {

        return EntityDTO.<OrderDTO>builder()
                .items(List.of(OrderDTO.builder().build()))
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
