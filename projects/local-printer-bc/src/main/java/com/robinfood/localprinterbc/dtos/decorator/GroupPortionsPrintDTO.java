package com.robinfood.localprinterbc.dtos.decorator;

import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductGroupPortionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class GroupPortionsPrintDTO {

    private Boolean hasItem;

    private String title;

    private List<OrderDetailProductGroupPortionDTO> items;
}
