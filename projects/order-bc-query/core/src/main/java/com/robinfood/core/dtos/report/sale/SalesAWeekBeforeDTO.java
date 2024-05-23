
package com.robinfood.core.dtos.report.sale;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class SalesAWeekBeforeDTO {

    @NotBlank
    @Schema(example = "42.325")
    public BigDecimal value;

}
