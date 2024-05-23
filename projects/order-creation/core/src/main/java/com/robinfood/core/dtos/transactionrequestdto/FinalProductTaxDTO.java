package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinalProductTaxDTO implements Serializable {

    private static final long serialVersionUID = 7109244313879046250L;

    @NotNull
    @Positive
    private Long articleId;

    @NotNull
    @Positive
    private Long articleTypeId;

    @NotNull
    @Positive
    private Long dicTaxId;

    private Long familyId;

    @NotNull
    @Positive
    private Long familyTaxTypeId;

    @NotBlank
    private String taxTypeName;

    @NotNull
    @Min(0)
    private BigDecimal taxPrice;

    private Long taxId;

    private Long taxTypeId;

    @NotNull
    @Min(0)
    private BigDecimal taxValue;

}
