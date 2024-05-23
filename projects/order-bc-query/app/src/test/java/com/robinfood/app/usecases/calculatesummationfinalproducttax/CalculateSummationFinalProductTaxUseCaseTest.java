package com.robinfood.app.usecases.calculatesummationfinalproducttax;

import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.dtos.request.order.BrandDTO;
import com.robinfood.core.dtos.request.order.FinalProductArticleDTO;
import com.robinfood.core.dtos.request.order.FinalProductCategoryDTO;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.order.FinalProductSizeDTO;
import com.robinfood.core.dtos.request.order.FinalProductTaxDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CalculateSummationFinalProductTaxUseCaseTest {

    @InjectMocks
    private CalculateSummationFinalProductTaxUseCase calculateSummationFinalProductTaxUseCase;

    @Test
    void test_CalculateSummationAdditions_When_Save_Success() {
        List<FinalProductTaxDTO> taxesDTO = new ArrayList<>();

        taxesDTO.add(new FinalProductTaxDTO(
                1L,
                1L,
                1L,
                1L,
                1L,
                1L,
                1L,
                1L,
                1000.0,
                1L,
                "IMPOCOSUMO",
                1L,
                1.0
        ));

        final FinalProductDTO finalProductDTO = new FinalProductDTO(
                new FinalProductArticleDTO(
                        1L,
                        1L,
                        1L
                ),
                new BrandDTO(
                        1L,
                        1L,
                        "Muy"
                ),
                new FinalProductCategoryDTO(
                        1L,
                        "Sugerido"
                ),
                List.of(new DeductionDTO(
                       1L,
                        BigDecimal.valueOf(2000)
                )),
                1L,
                null,
                0.0,
                1L,
                1L,
                "img.jpg",
                "Muy Cubano",
                null,
                1,
                null,
                new FinalProductSizeDTO(
                        1L,
                        "Muy"
                ),
                "sku",
                1L,
                taxesDTO,
                BigDecimal.ZERO,
                8900.0
        );

        Double result = calculateSummationFinalProductTaxUseCase.invoke(finalProductDTO);

        Assertions.assertEquals(result, 1000.0);
    }
}
