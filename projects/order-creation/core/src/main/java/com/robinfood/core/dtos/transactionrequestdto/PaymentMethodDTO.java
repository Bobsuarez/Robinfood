package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDTO implements Serializable {

    private static final long serialVersionUID = -3719514666801125588L;

    @NotNull
    @Positive
    private Long id;

    @NotNull
    @Positive
    private Long originId;

    private PaymentMethodDetailDTO detail;

    @NotNull
    @Min(value = DEFAULT_INTEGER_VALUE)
    private BigDecimal value;
}
