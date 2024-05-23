package com.robinfood.app.usecases.getordertotaldailysales;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.OrderTotalDailySalesDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.ordertotaldailysales.IOrderTotalDailySalesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GetOrderTotalDailySalesUseCaseTest {

    @Mock
    private IOrderTotalDailySalesRepository mockOrderTotalDailySalesRepository;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @InjectMocks
    private GetOrderTotalDailySalesUseCase getOrderTotalDailySalesUseCase;

    @Test
    void test_OrderTotalDailySales_OK() {

        OrderTotalDailySalesDTO orderTotalDailySales =
            new OrderTotalDailySalesDTO("10.0", 1, "Prueba", 10);
        List<OrderTotalDailySalesDTO> orderTotalDailySalesList = new ArrayList<>();
        orderTotalDailySalesList.add(orderTotalDailySales);

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke())
            .thenReturn(TokenModel.builder()
                .accessToken(token)
                .build());

        when(mockOrderTotalDailySalesRepository.getOrderTotalDailySales(token, 1, LocalDate.parse("2022-05-05")))
            .thenReturn(new Result.Success<>(orderTotalDailySalesList));

        final Result<List<OrderTotalDailySalesDTO>> result =
            getOrderTotalDailySalesUseCase.invoke(1, LocalDate.parse("2022-05-05"));

        assertEquals(orderTotalDailySalesList, ((Result.Success<List<OrderTotalDailySalesDTO>>) result).getData());

    }

}
