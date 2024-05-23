package com.robinfood.app.usecases.getorderhistoryitems;

import com.robinfood.app.datamocks.dto.input.OrderHistoryItemDTODataMock;
import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.core.dtos.OrderHistoryItemDTO;
import com.robinfood.repository.orders.IOrdersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderHistoryItemsUseCaseTest {

    @Mock
    private IOrdersRepository ordersRepository;

    @InjectMocks
    private GetOrderHistoryItemsUseCase getOrderHistoryItemsUseCase;

    private final List<Long> deliveryTypeIds = new ArrayList<>();
    private final LocalDate currentTime = LocalDate.of(2021, 6, 3);
    private final LocalDateTime initialTime = LocalDateTime.of(2021, 6, 3, 0, 0, 0);
    private final LocalDateTime finalTime = LocalDateTime.of(2021, 6, 3, 23, 59, 59);
    private final PageRequest pageRequest = PageRequest.of(1, 10);
    private final Long storeId = 27L;

    private final OrderEntityMock orderEntityMock = new OrderEntityMock();

    private final OrderHistoryItemDTODataMock orderHistoryItemDTOMock = new OrderHistoryItemDTODataMock();

    private final List<OrderHistoryItemDTO> orderHistoryItemDTOList = orderHistoryItemDTOMock.getDataDefaultList();

    @Test
    void test_GetOrderHistoryItems_When_OriginId_Is_Not_Null_And_Paid_Is_Null() {
        final Long originId = 1L;
        lenient().when(ordersRepository
                .findAllByOriginIdAndStoreIdAndDeliveryTypeIdInAndCreatedAtBetweenOrderByCreatedAtDesc(
                        originId,
                        storeId,
                        deliveryTypeIds,
                        initialTime,
                        finalTime,
                        pageRequest
                )).thenReturn(new PageImpl<>(orderEntityMock.getDataDefaultList()));

        final Page<OrderHistoryItemDTO> result = getOrderHistoryItemsUseCase
                .invoke(currentTime, deliveryTypeIds, originId, pageRequest, null, 27L);
        Assertions.assertEquals(orderHistoryItemDTOList, result.getContent());
    }

    @Test
    void test_GetOrderHistoryItems_When_OriginId_Is_Not_Null_With_Paid() {
        final Long originId = 1L;
        when(ordersRepository.findAllByOriginIdAndStoreIdAndDeliveryTypeIdInAndPaidAndCreatedAtBetweenOrderByCreatedAtDesc(
                originId,
                storeId,
                deliveryTypeIds,
                true,
                initialTime,
                finalTime,
                pageRequest
        )).thenReturn(new PageImpl<>(orderEntityMock.getDataDefaultList()));

        final Page<OrderHistoryItemDTO> result = getOrderHistoryItemsUseCase
                .invoke(currentTime, deliveryTypeIds, originId, pageRequest, true, 27L);
        Assertions.assertEquals(orderHistoryItemDTOList, result.getContent());
    }

    @Test
    void test_GetOrderHistoryItems_When_OriginId_Is_Null_And_Paid_Is_Null() {

        lenient().when(ordersRepository
                .findAllByStoreIdAndDeliveryTypeIdInAndCreatedAtBetweenOrderByCreatedAtDesc(
                        storeId,
                        deliveryTypeIds,
                        initialTime,
                        finalTime,
                        pageRequest
                )).thenReturn(new PageImpl<>(orderEntityMock.getDataDefaultList()));

        final Page<OrderHistoryItemDTO> result = getOrderHistoryItemsUseCase
                .invoke(currentTime, deliveryTypeIds, null, pageRequest, null, 27L);
        Assertions.assertEquals(orderHistoryItemDTOList, result.getContent());
    }

    @Test
    void test_GetOrderHistoryItems_When_OriginId_Is_Null_And_Paid_Is_Not_Null() {
        when(ordersRepository.findAllByStoreIdAndDeliveryTypeIdInAndPaidAndCreatedAtBetweenOrderByCreatedAtDesc(
                storeId,
                deliveryTypeIds,
                false,
                initialTime,
                finalTime,
                pageRequest
        )).thenReturn(new PageImpl<>(orderEntityMock.getDataDefaultList()));

        final Page<OrderHistoryItemDTO> result = getOrderHistoryItemsUseCase
                .invoke(currentTime, deliveryTypeIds, null, pageRequest, false, 27L);

        Assertions.assertEquals(orderHistoryItemDTOList, result.getContent());
    }

    @Test
    void test_GetOrderHistoryItems_When_CreatedAt_Is_Null() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime initialDateTime = LocalDateTime
                .of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
        final LocalDateTime endDateTime = LocalDateTime
                .of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59, 59);
        when(ordersRepository.findAllByStoreIdAndDeliveryTypeIdInAndPaidAndCreatedAtBetweenOrderByCreatedAtDesc(
                storeId,
                deliveryTypeIds,
                false,
                initialDateTime,
                endDateTime,
                pageRequest
        )).thenReturn(new PageImpl<>(orderEntityMock.getDataDefaultList()));

        final Page<OrderHistoryItemDTO> result = getOrderHistoryItemsUseCase
                .invoke(null, deliveryTypeIds, null, pageRequest, false, 27L);

        Assertions.assertEquals(orderHistoryItemDTOList, result.getContent());
    }

}
