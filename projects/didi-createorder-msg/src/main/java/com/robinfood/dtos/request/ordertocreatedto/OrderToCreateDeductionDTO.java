package com.robinfood.dtos.request.ordertocreatedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderToCreateDeductionDTO implements Serializable {
    private Long id;
    private BigDecimal value;
}
