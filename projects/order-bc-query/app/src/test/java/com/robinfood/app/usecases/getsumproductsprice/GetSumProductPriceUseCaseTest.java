package com.robinfood.app.usecases.getsumproductsprice;

import com.robinfood.app.datamocks.dto.input.FinalProductDTODataMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetSumProductPriceUseCaseTest {
    private final FinalProductDTODataMock finalProductDTOMock = new FinalProductDTODataMock();

    @InjectMocks
    private GetSumProductPriceUseCase getSumProductPriceUseCase;

    @Test
    void test_SumProductsPrice_When_Pass_Correct_Data() {
        final Double result = getSumProductPriceUseCase.invoke(finalProductDTOMock.getDataForCalculate(1L));
        Assertions.assertEquals(8900.0 + 10900.0, result);
    }
}
