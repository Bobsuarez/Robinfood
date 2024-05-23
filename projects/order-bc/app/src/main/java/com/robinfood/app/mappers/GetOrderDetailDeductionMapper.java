package com.robinfood.app.mappers;

import com.robinfood.core.dtos.GetOrderDetailDeductionDTO;
import com.robinfood.core.dtos.OrderDeductionFinalProductDTO;
import com.robinfood.core.entities.OrderDeductionEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetOrderDetailDeductionMapper {

    public static GetOrderDetailDeductionDTO orderDeductionEntityToGetOrderDetailDeductionDTO(
            OrderDeductionEntity orderDeductionEntity) {

        return GetOrderDetailDeductionDTO
                .builder()
                .id(orderDeductionEntity.getOrderId())
                .value(orderDeductionEntity.getValue())
                .build();
    }

    public static GetOrderDetailDeductionDTO orderDeductionFinalProductDtoToGetOrderDetailDeductionDTO(
            OrderDeductionFinalProductDTO orderDeductionFinalProductDTO) {

        return GetOrderDetailDeductionDTO
                .builder()
                .id(orderDeductionFinalProductDTO.getProductFinalId())
                .value(orderDeductionFinalProductDTO.getValue())
                .build();
    }

}
