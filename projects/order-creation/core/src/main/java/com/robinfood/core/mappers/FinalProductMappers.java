package com.robinfood.core.mappers;

import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDiscountDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductTaxDTO;
import com.robinfood.core.entities.menuvalidationentities.MenuValidationProductEntity;
import java.math.BigDecimal;

import static com.robinfood.core.constants.GlobalConstants.TAX_TYPE;
import static kotlin.collections.CollectionsKt.mapNotNull;

public final class FinalProductMappers {

    private FinalProductMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static MenuValidationProductEntity toMenuValidationProductEntity(FinalProductDTO finalProductDTO) {

        BigDecimal discountValue = BigDecimal.ZERO;
        BigDecimal taxValue = BigDecimal.ZERO;

        if (!finalProductDTO.getDiscounts().isEmpty()) {
            discountValue = finalProductDTO.getDiscounts()
                    .stream()
                    .map(FinalProductDiscountDTO::getValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        if (!finalProductDTO.getTaxes().isEmpty()) {
            taxValue = finalProductDTO.getTaxes()
                    .stream()
                    .map(FinalProductTaxDTO::getTaxPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        
        return new MenuValidationProductEntity(
                finalProductDTO.getBrand().getId(),
                discountValue,
                mapNotNull(finalProductDTO.getGroups(), GroupMappers::toGroupEntity),
                finalProductDTO.getArticle().getMenuHallProductId(),
                finalProductDTO.getArticle().getId(),
                finalProductDTO.getPrice(),
                finalProductDTO.getQuantity(),
                finalProductDTO.getSku(),
                finalProductDTO.getTotalPrice(),
                taxValue,
                TAX_TYPE,
                finalProductDTO.getArticle().getTypeName()
        );
    }

}
