package com.robinfood.app.usecases.getsumproducttaxesprice;

import com.robinfood.core.dtos.request.order.FinalProductTaxDTO;
import kotlin.collections.CollectionsKt;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Implementation of IGetSumProductTaxPriceUseCase
 */
@Component
@Slf4j
public class GetSumProductTaxPriceUseCase implements IGetSumProductTaxPriceUseCase {

    @Override
    public Double invoke(List<List<FinalProductTaxDTO>> listTaxesByProductsDTOS) {
        log.info("Starting process to sum product  tax price");

        final List<FinalProductTaxDTO> finalProductTaxDTOs = new ArrayList<>();

        for (List<FinalProductTaxDTO> orderFinalProductTaxDTOList : listTaxesByProductsDTOS) {
            if (orderFinalProductTaxDTOList != null) {
                finalProductTaxDTOs.addAll(orderFinalProductTaxDTOList);
            }
        }

        return CollectionsKt.sumByDouble(
            finalProductTaxDTOs,
            FinalProductTaxDTO::getTaxPrice
        );
    }
}
