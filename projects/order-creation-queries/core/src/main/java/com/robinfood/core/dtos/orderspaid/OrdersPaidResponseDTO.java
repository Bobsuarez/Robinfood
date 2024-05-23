package com.robinfood.core.dtos.orderspaid;

import com.robinfood.core.dtos.PaginationDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrdersPaidResponseDTO {

    private final List<OrderPaidResponseDTO> items;

    private PaginationDTO pagination;
}
