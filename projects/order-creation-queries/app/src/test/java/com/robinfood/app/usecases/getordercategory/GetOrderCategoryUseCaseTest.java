package com.robinfood.app.usecases.getordercategory;

import com.robinfood.app.mocks.OrderCategoriesDTOMock;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.ordercategories.DataRequestOrderCategoryDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.ordercategory.IOrderCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetOrderCategoryUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IOrderCategoryRepository iOrderCategoryRepository;

    @InjectMocks
    private GetOrderCategoryUseCase getOrderCategoryUseCase;

    @Test
    void Test_invoke_Should_ReturnOrderHistory_When_InvokeTheUseCase() {

        final String token = "token";
        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );
        when(iOrderCategoryRepository.getOrderListCategories(any(DataRequestOrderCategoryDTO.class), anyString()))
                .thenReturn(new Result.Success<>(OrderCategoriesDTOMock.getDataOrderCategoryList()));

        getOrderCategoryUseCase.invoke(DataRequestOrderCategoryDTO.builder().build());

        verify(iOrderCategoryRepository)
                .getOrderListCategories(
                        any(DataRequestOrderCategoryDTO.class),
                        anyString()
                );

    }
}
