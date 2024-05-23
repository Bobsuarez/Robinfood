package com.robinfood.app.usecases.getbrandidsbyorder;

import com.robinfood.core.dtos.request.order.BrandDTO;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import kotlin.collections.CollectionsKt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of IGetBrandIdsByOrder
 */
@Component
public class GetBrandIdsByOrder implements IGetBrandIdsByOrder{

    @Override
    public List<Long> invoke(List<FinalProductDTO> finalProductDTOList) {
        final  List<BrandDTO> brandDTOList = CollectionsKt.map(finalProductDTOList, FinalProductDTO::getBrand);
        final List<Long> brandIds = CollectionsKt.map(brandDTOList, BrandDTO::getId);

        return brandIds.stream().distinct().collect(Collectors.toList());
    }
}
