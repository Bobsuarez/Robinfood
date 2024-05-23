package com.robinfood.dtos.sendordertosimba.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BIG_DECIMAL_VALUE;
import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.constants.GeneralConstants.DEFAULT_STRING_EMPTY;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DeliveryDTO {

    private String addressCity = DEFAULT_STRING_EMPTY;

    private String addressDescription = DEFAULT_STRING_EMPTY;

    private LocalDate arrivalDate = LocalDate.now();

    private LocalTime arrivalTime = LocalTime.now();

    private String code = DEFAULT_STRING_EMPTY;

    private String integrationId = DEFAULT_STRING_EMPTY;

    private String notes = DEFAULT_STRING_EMPTY;

    private Integer orderType = DEFAULT_INTEGER;

    private String paymentMethod = DEFAULT_STRING_EMPTY;

    private BigDecimal totalDelivery = DEFAULT_BIG_DECIMAL_VALUE;

    private BigDecimal totalDiscount = DEFAULT_BIG_DECIMAL_VALUE;

    private BigDecimal totalTip = DEFAULT_BIG_DECIMAL_VALUE;

    private String type = DEFAULT_STRING_EMPTY;

    private String userName = DEFAULT_STRING_EMPTY;

}
