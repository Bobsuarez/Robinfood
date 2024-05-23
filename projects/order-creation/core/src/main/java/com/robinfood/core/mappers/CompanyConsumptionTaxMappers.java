package com.robinfood.core.mappers;

import com.robinfood.core.dtos.CompanyConsumptionTaxDTO;
import com.robinfood.core.entities.CompanyConsumptionTaxEntity;

public final class CompanyConsumptionTaxMappers {

    private CompanyConsumptionTaxMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static CompanyConsumptionTaxDTO toCompanyDiscountTaxDTO(
            CompanyConsumptionTaxEntity companyConsumptionTaxEntity
    ) {
        return new CompanyConsumptionTaxDTO(
                companyConsumptionTaxEntity.getCompanyName(),
                companyConsumptionTaxEntity.getConsumptionValue()
        );
    }
}
