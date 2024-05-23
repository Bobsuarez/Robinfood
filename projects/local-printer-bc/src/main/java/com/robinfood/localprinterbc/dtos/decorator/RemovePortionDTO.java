package com.robinfood.localprinterbc.dtos.decorator;

import com.robinfood.localprinterbc.dtos.orders.OrderDetailRemovedPortionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RemovePortionDTO {

    private Boolean hasItem;

    private String title;

    private List<OrderDetailRemovedPortionDTO> items;

}
