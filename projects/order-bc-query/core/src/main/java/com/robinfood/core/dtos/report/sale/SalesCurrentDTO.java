
package com.robinfood.core.dtos.report.sale;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Builder
@Setter
@Getter
public class SalesCurrentDTO {

    @NotBlank
    @Schema(example = "44.003")
    public BigDecimal value;

}
