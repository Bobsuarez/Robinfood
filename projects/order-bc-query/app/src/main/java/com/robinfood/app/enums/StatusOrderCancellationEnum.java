package com.robinfood.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;

@AllArgsConstructor
@Getter
public enum StatusOrderCancellationEnum {

    ORDER_CANCELED("6,8", "ORDER_DELETED"),
    ORDER_PAID("1,2,3,4,5,18", "ORDER_CREATED");

    private final String codesStatus;
    private final String operationStatus;

    public static List<Long> parseToArrayStatus(String status) {

        String toStringCode = Arrays.stream(values())
                .filter(data -> data.name().equals(status))
                .findFirst()
                .orElse(ORDER_PAID)
                .getCodesStatus();

        return Arrays.stream(toStringCode.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }

    public static String parseToStatus(Long codeStatus) {

        return Arrays.stream(StatusOrderCancellationEnum.values())
                .filter(status -> Arrays.asList(status.getCodesStatus().split(","))
                        .contains(codeStatus.toString()))
                .map(StatusOrderCancellationEnum::getOperationStatus)
                .findFirst()
                .orElse(DEFAULT_STRING_VALUE);
    }
}
