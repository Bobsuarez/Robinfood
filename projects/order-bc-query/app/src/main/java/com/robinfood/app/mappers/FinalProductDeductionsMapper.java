package com.robinfood.app.mappers;

import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.dtos.OrderDeductionFinalProductDTO;
import com.robinfood.core.entities.OrderDeductionFinalProductEntity;
import java.util.List;
import java.util.stream.Collectors;

public class FinalProductDeductionsMapper {

    public static List<OrderDeductionFinalProductEntity> listDeductionDtoToListOrderDeductionProductEntities(
            List<DeductionDTO> deduction,
            Long idOrder,
            Long idFinalProduct) {

        return deduction.stream()
                .map(item ->
                        FinalProductDeductionsMapper
                                .deductionDTOToOrderDeductionProductEntity(item, idOrder, idFinalProduct))
                .collect(Collectors.toList());

    }

    public static OrderDeductionFinalProductEntity deductionDTOToOrderDeductionProductEntity(
            DeductionDTO deductionDTO,
            Long idOrder,
            Long idFinalProduct) {

        return OrderDeductionFinalProductEntity
                .builder()
                .value(deductionDTO.getValue())
                .productFinalId(idFinalProduct)
                .orderId(idOrder)
                .build();
    }

    public static OrderDeductionFinalProductDTO orderDeductionProductEntityToDTO(
            OrderDeductionFinalProductEntity orderDeductionFinalProductEntity) {

        return OrderDeductionFinalProductDTO
                .builder()
                .id(orderDeductionFinalProductEntity.getId())
                .productFinalId(orderDeductionFinalProductEntity.getProductFinalId())
                .orderId(orderDeductionFinalProductEntity.getOrderId())
                .value(orderDeductionFinalProductEntity.getValue())
                .build();
    }
}
