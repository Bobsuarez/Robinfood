package com.robinfood.paymentmethodsbc.dto.steps;

import com.robinfood.paymentmethodsbc.enums.StepType;
import java.util.List;
import lombok.Data;
import lombok.NonNull;

@Data
public class StepGroupDTO {
    @NonNull
    private final List<StepDTO> steps;

    @Data
    public static class StepDTO {
        private final StepType type;
    }
}
