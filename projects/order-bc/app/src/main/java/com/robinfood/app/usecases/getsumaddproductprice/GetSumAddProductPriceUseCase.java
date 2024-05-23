package com.robinfood.app.usecases.getsumaddproductprice;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.order.FinalProductPortionDTO;
import kotlin.collections.CollectionsKt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of IGetSumAddProductPriceUseCase
 */
@Component
public class GetSumAddProductPriceUseCase implements  IGetSumAddProductPriceUseCase{
    
    @Override
    public Double invoke(List<FinalProductDTO> finalProductDTOS) {

        final List<List<FinalProductPortionDTO>> productPortionDTOS = CollectionsKt.map(
                finalProductDTOS,
                FinalProductDTO::getPortions
        );

        final List<FinalProductPortionDTO> portionDTOS = new ArrayList<>();

        for (List<FinalProductPortionDTO> portionDTOItems : productPortionDTOS) {
            portionDTOS.addAll(portionDTOItems);
        }

        return CollectionsKt.sumByDouble(
                CollectionsKt.filter(portionDTOS, portionDTO -> portionDTO.getFree().equals(DEFAULT_INTEGER_VALUE)),
                FinalProductPortionDTO::getPrice
        );
    }
}
