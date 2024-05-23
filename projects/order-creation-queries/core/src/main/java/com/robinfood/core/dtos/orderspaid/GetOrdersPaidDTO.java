package com.robinfood.core.dtos.orderspaid;

import com.robinfood.core.dtos.PaginationDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetOrdersPaidDTO{

    private final List<OrderPaidDTO> items;

    private PaginationDTO pagination;
}
