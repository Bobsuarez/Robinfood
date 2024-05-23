package com.robinfood.app.usecases.calculatesummationfinalproductaddition;

import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.order.FinalProductPortionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_EMPTY_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

/**
 * Implementation of ICalculateSummationFinalProductAdditionUseCase
 */
@Component
@Slf4j
public class CalculateFinalProductAdditionUseCase implements ICalculateFinalProductAdditionUseCase {

    @Override
    public Double invoke(FinalProductDTO finalProduct){
        log.info("calculating product additions by id: [{}]", finalProduct.getId());

        double totalPortionsByQuantity = DEFAULT_DOUBLE_EMPTY_VALUE;

        for (FinalProductPortionDTO finalProductPortionDTO: finalProduct.getPortions()) {

            var differenceBetweenFreeAndQuantity =
                    finalProductPortionDTO.getQuantity() - finalProductPortionDTO.getFree();

            if (differenceBetweenFreeAndQuantity > DEFAULT_INTEGER_VALUE) {
                totalPortionsByQuantity += finalProductPortionDTO.getPrice() * differenceBetweenFreeAndQuantity;
            }
        }

        return totalPortionsByQuantity * finalProduct.getQuantity();
    }
}
