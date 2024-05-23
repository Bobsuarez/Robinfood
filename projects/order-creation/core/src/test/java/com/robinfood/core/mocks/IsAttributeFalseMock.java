package com.robinfood.core.mocks;

import com.robinfood.core.validations.IsAttributeFalse;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@IsAttributeFalse(field = "isActive", required = {"statusId", "statusValue", "statusInt", "statusName",
        "isStatus", "statusData"})
public class IsAttributeFalseMock {

    @NotNull
    private Boolean isActive;

    private Boolean isStatus;

    private Object statusData;

    private Long statusId;

    private Integer statusInt;

    private String statusName;

    private Double statusValue;
}


