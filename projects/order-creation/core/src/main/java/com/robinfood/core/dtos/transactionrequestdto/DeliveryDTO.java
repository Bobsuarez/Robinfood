package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BIG_DECIMAL_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DeliveryDTO implements Serializable {

    private String addressCity = DEFAULT_STRING_VALUE;

    private String addressDescription = DEFAULT_STRING_VALUE;

    private LocalDate arrivalDate = LocalDate.now();

    private LocalTime arrivalTime = LocalTime.now();

    private String code = DEFAULT_STRING_VALUE;

    private String integrationId = DEFAULT_STRING_VALUE;

    private String notes = DEFAULT_STRING_VALUE;

    private Integer orderType = DEFAULT_INTEGER_VALUE;

    private String paymentMethod = DEFAULT_STRING_VALUE;

    @Min(0)
    private BigDecimal totalDelivery = DEFAULT_BIG_DECIMAL_VALUE;

    @Min(0)
    private BigDecimal totalDiscount = DEFAULT_BIG_DECIMAL_VALUE;

    @Min(0)
    private BigDecimal totalTip = DEFAULT_BIG_DECIMAL_VALUE;

    private String type = DEFAULT_STRING_VALUE;

    private String userName = DEFAULT_STRING_VALUE;

}
