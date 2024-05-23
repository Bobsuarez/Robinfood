package com.robinfood.app.mappers;

import com.robinfood.core.dtos.deductions.TypeDeductionsDTO;
import com.robinfood.core.entities.OrderTypeDeductionsEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderTypeDeductionMapper {

    public static Map<Long, String> listOrderTypeDeducctionsEntitiesToListTypeDeductionsDto (
            List<OrderTypeDeductionsEntity> orderTypeDeductionsEntityList) {

        return orderTypeDeductionsEntityList.stream()
                .collect(Collectors.toMap(OrderTypeDeductionsEntity::getId, OrderTypeDeductionsEntity::getName));
    }
}
