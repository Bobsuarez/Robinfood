package com.robinfood.app.mappers;

import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.entities.OrderDeductionEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDeductionMapper {

    public static List<OrderDeductionEntity> listOrderDeductionDTOToListOrderDeductionEntity (
            List<DeductionDTO> listdeductionDTO,
            Long idOrder) {
            return listdeductionDTO.stream().map(deduction ->
                    OrderDeductionMapper.orderDeductionDTOToOrderDeductionEntity(deduction,idOrder))
                    .collect(Collectors.toList());
    }

    public static OrderDeductionEntity orderDeductionDTOToOrderDeductionEntity (DeductionDTO deductionDTO, Long idOrder) {
        return OrderDeductionEntity
                .builder()
                .value(deductionDTO.getValue())
                .orderId(idOrder)
                .build();
    }
}
