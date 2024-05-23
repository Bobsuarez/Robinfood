package com.robinfood.core.mocks;

import com.robinfood.core.validations.IsAttributeFalse;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@IsAttributeFalse(field = "isActive", required = {"statusId", "statusValue", "statusInt", "statusName",
        "isStatus"})
public class IsAttributeFalseNoGettersMock {

    @NotNull
    @Getter
    private Boolean isActive;

    private Long statusId;
}
