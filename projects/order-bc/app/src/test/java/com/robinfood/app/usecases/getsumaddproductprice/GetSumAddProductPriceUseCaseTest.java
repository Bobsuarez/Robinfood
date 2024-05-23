package com.robinfood.app.usecases.getsumaddproductprice;

import com.robinfood.app.datamocks.dto.input.FinalProductDTODataMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetSumAddProductPriceUseCaseTest {

    private final FinalProductDTODataMock finalProductDTOMock = new FinalProductDTODataMock();

    @InjectMocks
    private GetSumAddProductPriceUseCase getSumAddProductPriceUseCase;

    @Test
    void test_SumAddProductPrice_When_Pass_Correct_Data() {
        final Double result = getSumAddProductPriceUseCase.invoke(finalProductDTOMock.getDataForCalculate(1L));
        Assertions.assertEquals(400.0, result);
    }
}
