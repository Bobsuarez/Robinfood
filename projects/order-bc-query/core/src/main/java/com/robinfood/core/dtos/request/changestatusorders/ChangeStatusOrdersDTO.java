package com.robinfood.core.dtos.request.changestatusorders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ChangeStatusOrdersDTO {

    private List<OrderStatusDTO> orders;
}
