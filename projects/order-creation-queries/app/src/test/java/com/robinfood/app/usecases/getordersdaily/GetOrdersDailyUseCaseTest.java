package com.robinfood.app.usecases.getordersdaily;

import com.robinfood.app.mocks.BrandDTOMock;
import com.robinfood.app.mocks.MenuBrandDTOMock;
import com.robinfood.app.mocks.OrderDailyDTOMock;
import com.robinfood.app.mocks.StoreDTOMock;
import com.robinfood.app.usecases.gettokenbusinesscapability.GetTokenBusinessCapabilityUseCase;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.configuration.store.IStoreRepository;
import com.robinfood.repository.menubrandstore.MenuBrandStoreRepository;
import com.robinfood.repository.orderdaily.OrderDailyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrdersDailyUseCaseTest {

    @Mock
    private GetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private MenuBrandStoreRepository menuBrandStoreRepository;

    @Mock
    private OrderDailyRepository orderDailyRepository;

    @Mock
    private IStoreRepository storeRepository;

    @InjectMocks
    private GetOrdersDailyUseCase getOrdersDailyUseCase;

    @Test
    void test_Get_Orders_Daily_Returns_Correctly() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(orderDailyRepository.getOrderDaily(27L, "time", token))
                .thenReturn(new Result.Success<>(List.of(OrderDailyDTOMock.getDataDefault())));

        when(menuBrandStoreRepository.getMenuBrandStore(27L, token))
                .thenReturn(new Result.Success<>(List.of(MenuBrandDTOMock.getDataDefault())));

        when(storeRepository.getStore(27L, token))
                .thenReturn(new Result.Success<>(StoreDTOMock.getDataDefault()));

        getOrdersDailyUseCase.invoke(27L, "time");

        verify(getTokenBusinessCapabilityUseCase).invoke();
        verify(orderDailyRepository).getOrderDaily(anyLong(), anyString(), anyString());
        verify(menuBrandStoreRepository).getMenuBrandStore(anyLong(), anyString());
    }

    @Test
    void test_Get_Orders_Daily_Returns_Correctly_Not_Found_Brand() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(orderDailyRepository.getOrderDaily(27L, "time", token))
                .thenReturn(new Result.Success<>(List.of(OrderDailyDTOMock.getDataDefaultNotFoundBrand())));

        when(menuBrandStoreRepository.getMenuBrandStore(27L, token))
                .thenReturn(new Result.Success<>(List.of(MenuBrandDTOMock.getDataDefault())));

        when(storeRepository.getStore(27L, token))
                .thenReturn(new Result.Success<>(StoreDTOMock.getDataDefault()));

        getOrdersDailyUseCase.invoke(27L, "time");

        verify(getTokenBusinessCapabilityUseCase).invoke();
        verify(orderDailyRepository).getOrderDaily(anyLong(), anyString(), anyString());
        verify(menuBrandStoreRepository).getMenuBrandStore(anyLong(), anyString());
    }

    @Test
    void test_Get_Orders_Daily_Returns_Correctly_Error_Order_BC() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(orderDailyRepository.getOrderDaily(27L, "time", token))
                .thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

        assertThrows(ResponseStatusException.class, () -> {
            getOrdersDailyUseCase.invoke(27L, "time");
        });
    }

    @Test
    void test_Get_Orders_Daily_Returns_Correctly_Error_Menu_BC() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(orderDailyRepository.getOrderDaily(27L, "time", token))
                .thenReturn(new Result.Success<>(List.of(OrderDailyDTOMock.getDataDefault())));

        when(menuBrandStoreRepository.getMenuBrandStore(27L, token))
                .thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

        assertThrows(ResponseStatusException.class, () -> {
            getOrdersDailyUseCase.invoke(27L, "time");
        });
    }

    @Test
    void test_Get_Store_Returns_Correctly_Error_Menu_BC() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(orderDailyRepository.getOrderDaily(27L, "time", token))
                .thenReturn(new Result.Success<>(List.of(OrderDailyDTOMock.getDataDefault())));

        when(menuBrandStoreRepository.getMenuBrandStore(27L, token))
                .thenReturn(new Result.Success<>(List.of(MenuBrandDTOMock.getDataDefault())));

        when(storeRepository.getStore(27L, token))
                .thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

        assertThrows(ResponseStatusException.class, () -> {
            getOrdersDailyUseCase.invoke(27L, "time");
        });
    }
}