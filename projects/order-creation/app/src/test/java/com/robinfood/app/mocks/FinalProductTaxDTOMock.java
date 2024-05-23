package com.robinfood.app.mocks;

import com.robinfood.core.dtos.transactionrequestdto.FinalProductCategoryDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductTaxDTO;

import java.math.BigDecimal;

public class FinalProductTaxDTOMock {

    public static FinalProductTaxDTO build() {
        return FinalProductTaxDTO
                .builder()
                .articleId(3L)
                .articleTypeId(1L)
                .dicTaxId(1L)
                .familyId(1L)
                .familyTaxTypeId(1L)
                .taxTypeName("Tax")
                .taxPrice(new BigDecimal("1029.6296"))
                .taxId(1L)
                .taxTypeId(1L)
                .taxValue(new BigDecimal("8.0"))
                .familyTaxTypeId(7L)
                .build();
    }
}
