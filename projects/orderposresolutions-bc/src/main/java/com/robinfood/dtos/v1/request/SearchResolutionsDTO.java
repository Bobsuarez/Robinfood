package com.robinfood.dtos.v1.request;

import com.robinfood.enums.OrderByEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SearchResolutionsDTO implements Serializable {

    private Boolean status;

    private Integer page;

    private OrderByEnum orderByEnum;

    private Long resolutionId;

    private Integer size;

    private String valueCustomFilter;

    private Boolean withPos;

    public static SearchResolutionsDTO getSearchResolutionsDTO(
            String orderByEndDate,
            String page,
            String resolutionId,
            String size,
            String status,
            String valueCustomFilter,
            String withPos
    ){

        return SearchResolutionsDTO.builder()
                .status(convertStatusToBoolean(status))
                .page(convertPageStringToInteger(page))
                .orderByEnum(getOrderByEndDate(orderByEndDate))
                .size(convertSizeStringToInteger(size))
                .valueCustomFilter(getValueCustomFilter(valueCustomFilter))
                .withPos(convertWithPosToBoolean(withPos))
                .resolutionId(convertStringToLong(resolutionId))
                .build();

    }

    private static Integer convertPageStringToInteger(String page){
        return Integer.parseInt(page);
    }

    private static Integer convertSizeStringToInteger(String size){
        return Integer.parseInt(size);
    }

    private static Boolean convertStatusToBoolean(String status){
        return (status != null && !status.trim().isEmpty()) ? Boolean.parseBoolean(status) : null;
    }

    private static String getValueCustomFilter(String valueCustomFilter){
        return (valueCustomFilter != null && !valueCustomFilter.trim().isEmpty()) ? valueCustomFilter.trim() : null;
    }

    private static OrderByEnum getOrderByEndDate(String orderByEndDate){
        return (orderByEndDate!=null && !orderByEndDate.trim().isEmpty()) ?
                OrderByEnum.valueOf(orderByEndDate.toUpperCase()) : null;
    }

    private static Boolean convertWithPosToBoolean(String withPos){
        return (withPos != null && !withPos.trim().isEmpty()) ? Boolean.parseBoolean(withPos) : null;
    }

    private static Long convertStringToLong(String resolutionId){
        return (resolutionId != null && !resolutionId.trim().isEmpty()) ? Long.parseLong(resolutionId) : null;
    }

}
