package com.robinfood.app.usecases.getorderhistory;

import com.robinfood.app.datamocks.dto.core.UserDataDTOMock;
import com.robinfood.app.usecases.getdeliverytypes.IGetDeliveryTypesUseCase;
import com.robinfood.app.usecases.getorderdetailbyids.IGetOrderDetailByIdsUseCase;
import com.robinfood.app.usecases.getorderhistoryitems.IGetOrderHistoryItemsUseCase;
import com.robinfood.app.usecases.getstatusbyorderids.IGetStatusByOrderIds;
import com.robinfood.app.usecases.getuserdatabyorderids.IGetUserDataByOrderIdsUseCase;
import com.robinfood.core.dtos.*;
import kotlin.collections.CollectionsKt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrderHistoryUseCaseTest {

    @Mock
    private IGetDeliveryTypesUseCase getDeliveryTypesUseCase;

    @Mock
    private IGetOrderDetailByIdsUseCase getOrderDetailByIdsUseCase;

    @Mock
    private IGetOrderHistoryItemsUseCase getOrderHistoryItemsUseCase;

    @Mock
    private IGetStatusByOrderIds getStatusByOrderIds;

    @Mock
    private IGetUserDataByOrderIdsUseCase getUserDataByOrderIdsUseCase;

    @InjectMocks
    private GetOrderHistoryUseCase getOrderHistoryUseCase;

    private final Integer currentPage = 2;
    private final Integer perPage = 10;
    private final PageRequest pageRequest = PageRequest.of(currentPage - 1, perPage);
    private final String TIMEZONE_CO = "America/Bogota";

    private final List<DeliveryTypeDTO> deliveryTypeDTOS = new ArrayList<>(Arrays.asList(
            new DeliveryTypeDTO(
                    "description1",
                    1L,
                    false,
                    true,
                    false,
                    "delivery"
            ),
            new DeliveryTypeDTO(
                    "description2",
                    2L,
                    true,
                    false,
                    false,
                    "integration"
            ),
            new DeliveryTypeDTO(
                    "description3",
                    3L,
                    false,
                    false,
                    true,
                    "take_away"
            )
    ));

    private final List<Long> deliveryTypeIds = CollectionsKt.map(deliveryTypeDTOS, DeliveryTypeDTO::getId);

    private final List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>(Arrays.asList(
            new OrderDetailDTO(
                    3900.0,
                    true,
                    1L,
                    "1234",
                    "Notes"
            ),
            new OrderDetailDTO(
                    3900.0,
                    true,
                    2L,
                    "5678",
                    "Notes"
            )
    ));

    private final Page<OrderHistoryItemDTO> orderHistoryItemDTOS = new PageImpl<>(Arrays.asList(
            new OrderHistoryItemDTO(
                    "MUY",
                    "today",
                    1L,
                    1L,
                    "1234",
                    "Rappi",
                    1L,
                    7900.0
            ),
            new OrderHistoryItemDTO(
                    "El Original",
                    "yesterday",
                    2L,
                    2L,
                    "5678",
                    "Domicilios.com",
                    2L,
                    900.0
            )
    ));

    private final List<Long> orderHistoryItemIds = CollectionsKt.map(orderHistoryItemDTOS, OrderHistoryItemDTO::getId);

    private final List<Long> orderHistoryStatusesIds = CollectionsKt.map(orderHistoryItemDTOS, OrderHistoryItemDTO::getStatusId);

    private final List<UserDataDTO> userDataDTOS = new ArrayList<>(Arrays.asList(
            UserDataDTOMock.getDataDefault()
    ));

    private final HistoryDTO<OrderHistoryItemDTO> history = new HistoryDTO<>(
            orderHistoryItemDTOS.getContent(),
            new PropertyDTO(
                    currentPage,
                    perPage,
                    orderHistoryItemDTOS.getTotalPages(),
                    orderHistoryItemDTOS.getContent().size()
            )
    );

    private final List<OrderStatusDTO> orderStatusDTOS = new ArrayList<>(Arrays.asList(
            new OrderStatusDTO(
                    1L,
                    "On its way"
            ),
            new OrderStatusDTO(
                    2L,
                    "Canceled"
            ),
            new OrderStatusDTO(
                    3L,
                    "Ready to deliver"
            )
    ));

    @Test
    void test_GetOrderHistory_Returns_Correctly() {
        when(getDeliveryTypesUseCase.invoke(false, false, false))
                .thenReturn(deliveryTypeDTOS);
        final Long originId = 1L;
        final Long storeId = 27L;
        when(getOrderHistoryItemsUseCase.invoke(
                LocalDate.now(),
                deliveryTypeIds,
                originId,
                pageRequest,
                true,
                storeId
        )).thenReturn(orderHistoryItemDTOS);
        when(getUserDataByOrderIdsUseCase.invoke(orderHistoryItemIds))
                .thenReturn(userDataDTOS);
        when(getOrderDetailByIdsUseCase.invoke(orderHistoryItemIds))
                .thenReturn(orderDetailDTOS);
        when(getStatusByOrderIds.invoke(orderHistoryStatusesIds))
                .thenReturn(orderStatusDTOS);

        final HistoryDTO<OrderHistoryItemDTO> result = getOrderHistoryUseCase.invoke(
                TIMEZONE_CO,
                LocalDate.now(),
                currentPage,
                false,
                false,
                false,
                originId,
                true,
                perPage,
                storeId
        );

        assertEquals(history, result);
    }

    @Test
    void test_GetOrderHistory_Detail_And_Status_Is_Null() {

        when(getDeliveryTypesUseCase.invoke(false, false, false))
                .thenReturn(deliveryTypeDTOS);
        final Long originId = 1L;
        final Long storeId = 27L;
        when(getOrderHistoryItemsUseCase.invoke(
                LocalDate.now(),
                deliveryTypeIds,
                originId,
                pageRequest,
                true,
                storeId
        )).thenReturn(orderHistoryItemDTOS);
        when(getUserDataByOrderIdsUseCase.invoke(orderHistoryItemIds))
                .thenReturn(userDataDTOS);
        when(getOrderDetailByIdsUseCase.invoke(orderHistoryItemIds))
                .thenReturn(new ArrayList<>());
        when(getStatusByOrderIds.invoke(orderHistoryStatusesIds))
                .thenReturn(new ArrayList<>());

        final HistoryDTO<OrderHistoryItemDTO> result = getOrderHistoryUseCase.invoke(
                TIMEZONE_CO,
                LocalDate.now(),
                currentPage,
                false,
                false,
                false,
                originId,
                true,
                perPage,
                storeId
        );

        assertEquals(history, result);
    }
}
