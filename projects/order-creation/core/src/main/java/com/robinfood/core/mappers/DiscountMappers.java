package com.robinfood.core.mappers;

import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.DiscountContainerRequestDTO;
import com.robinfood.core.dtos.DiscountContainerResponseDTO;
import com.robinfood.core.dtos.DiscountQueryDTO;
import com.robinfood.core.dtos.transactionrequestdto.DiscountDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.entities.DiscountContainerRequestEntity;
import com.robinfood.core.entities.DiscountContainerResponseEntity;
import com.robinfood.core.entities.DiscountQueryEntity;

import java.math.BigDecimal;

import static kotlin.collections.CollectionsKt.map;

public final class DiscountMappers {

    private DiscountMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static DiscountContainerRequestEntity toDiscountContainerRequestEntity(
            DiscountContainerRequestDTO discount
    ) {
        return new DiscountContainerRequestEntity(
                discount.getDiscount(),
                discount.getName(),
                discount.getId(),
                discount.getQuantity(),
                discount.getProductValue()
        );
    }

    public static DiscountDTO builderDiscountDTO(
            DiscountDTO discountDTO,
            FinalProductDTO product,
            BigDecimal valueDiscount
    ) {

        discountDTO.setId(null);
        discountDTO.setIsConsumptionDiscount(Boolean.FALSE);
        discountDTO.setIsProductDiscount(Boolean.TRUE);
        discountDTO.setOrderFinalProductId(product.getId());
        discountDTO.setProductId(product.getId());
        discountDTO.setSKU(product.getSku());
        discountDTO.setTypeId(GlobalConstants.DISCOUNT_PRODUCT);
        discountDTO.setValue(valueDiscount);

        return discountDTO;
    }

    public static DiscountContainerResponseDTO toDiscountContainerResponseDTO(
            DiscountContainerResponseEntity discount
    ) {
        return new DiscountContainerResponseDTO(
                discount.getEntity(),
                discount.getQuantity(),
                discount.getReason(),
                discount.getSourceId(),
                discount.getValid(),
                discount.getValue()
        );
    }

    public static DiscountQueryEntity toDiscountQueryEntity(DiscountQueryDTO discountQuery) {
        return new DiscountQueryEntity(
                discountQuery.getCriteriaInfo(),
                map(
                        discountQuery.getDiscounts(),
                        DiscountMappers::toDiscountContainerRequestEntity
                )
        );
    }
}
