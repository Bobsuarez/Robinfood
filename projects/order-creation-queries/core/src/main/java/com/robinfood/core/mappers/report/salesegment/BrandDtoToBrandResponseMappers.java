package com.robinfood.core.mappers.report.salesegment;

import com.robinfood.core.dtos.report.salebysegment.response.BrandsDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.ItemDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.TotalDTOResponse;

import java.util.List;

public final class BrandDtoToBrandResponseMappers {

    private BrandDtoToBrandResponseMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static BrandsDTOResponse saleSegmentToResponse(
            List<ItemDTOResponse> itemBrandList,
            TotalDTOResponse totalBrand
    ) {

        return BrandsDTOResponse.builder()
                .items(itemBrandList)
                .total(totalBrand)
                .build();
    }
}
