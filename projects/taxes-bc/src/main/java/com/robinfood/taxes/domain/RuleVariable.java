package com.robinfood.taxes.domain;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleVariable {

    @NotNull
    private String type;

    @NotNull
    private String value;

}
