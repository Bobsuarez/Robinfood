package com.robinfood.core.entities;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CompanyConsumptionTaxEntity {

    private final String companyName;

    private final BigDecimal consumptionValue;
}
