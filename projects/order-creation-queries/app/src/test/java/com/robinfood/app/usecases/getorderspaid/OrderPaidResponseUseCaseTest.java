package com.robinfood.app.usecases.getorderspaid;

import com.robinfood.app.mocks.ConfigChannelsDTOMock;
import com.robinfood.app.mocks.GetOrderPaidDTOMock;
import com.robinfood.app.mocks.OrdersPaidResponseDTOMock;
import com.robinfood.app.mocks.StoreDTOMock;
import com.robinfood.app.mocks.configurations.BrandsDTOMock;
import com.robinfood.core.dtos.orderspaid.DataOrdersPaidRequestDTO;
import com.robinfood.core.dtos.orderspaid.GetOrdersPaidDTO;
import com.robinfood.core.dtos.orderspaid.OrdersPaidResponseDTO;;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.configuration.brands.IBrandsRepository;
import com.robinfood.repository.configuration.channels.IChannelRepository;
import com.robinfood.repository.configuration.store.IStoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderPaidResponseUseCaseTest {

    @Mock
    IBrandsRepository brandsRepository;

    @Mock
    IChannelRepository channelRepository;

    @Mock
    IStoreRepository storeRepository;

    @InjectMocks
    OrderPaidResponseUseCase orderPaidResponseUseCase;

    @Test
    void test_invoke_Should_Return_OK_When_InvokeTheUseCase(){

        when(brandsRepository.getAll(
                anyString())
        ).thenReturn(new Result.Success<>(BrandsDTOMock.getDataDefault()));

        when(channelRepository.getChannels(
                anyString())
        ).thenReturn(new Result.Success<>(ConfigChannelsDTOMock.getDataDefault()));

        when(storeRepository.getStore(
                anyLong(),
                anyString())
        ).thenReturn(new Result.Success<>(StoreDTOMock.getDataDefault()));

        final GetOrdersPaidDTO getOrdersPaidDTO = GetOrderPaidDTOMock.getDataDefault();

        assertAll(() -> orderPaidResponseUseCase.invoke(
                getOrdersPaidDTO,
                anyString()
        ));

        verify(brandsRepository).getAll(anyString());

        verify(channelRepository).getChannels(anyString());

        verify(storeRepository).getStore(anyLong(), anyString());

        OrdersPaidResponseDTO response = orderPaidResponseUseCase
                .invoke(getOrdersPaidDTO, anyString());

        assertTrue(response instanceof OrdersPaidResponseDTO);

        assertEquals(
                OrdersPaidResponseDTOMock.getDataDefault(),
                response
        );
    }
}
