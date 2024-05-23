package com.robinfood.app.usecases.getsumdiscountprice;

import com.robinfood.app.datamocks.dto.input.FinalProductDTODataMock;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetSumDiscountPriceUseCaseTest{

    private final FinalProductDTO finalProductDTODataMock =
        new FinalProductDTODataMock().getDataDefault(1L);

    @InjectMocks
    private GetSumDiscountPriceUseCase getSumDiscountPriceUseCase;

    @Test
    void test_SumDiscountPrice_When_Pass_Correct_Data() {
        final Double result = getSumDiscountPriceUseCase.invoke(
            Collections.singletonList(finalProductDTODataMock)
        );

        Assertions.assertEquals(8900.0, result);
    }

}
