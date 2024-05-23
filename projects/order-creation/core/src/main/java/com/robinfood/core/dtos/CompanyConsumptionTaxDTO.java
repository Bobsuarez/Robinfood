package com.robinfood.core.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CompanyConsumptionTaxDTO implements Serializable {

    private final String companyName;

    private final BigDecimal consumptionValue;
}
