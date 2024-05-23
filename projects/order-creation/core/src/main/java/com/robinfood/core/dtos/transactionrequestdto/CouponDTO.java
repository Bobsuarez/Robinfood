package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponDTO implements Serializable {

    private static final long serialVersionUID = 60512316832949847L;

    @NotBlank
    private String code;

    private BigDecimal value;

}
