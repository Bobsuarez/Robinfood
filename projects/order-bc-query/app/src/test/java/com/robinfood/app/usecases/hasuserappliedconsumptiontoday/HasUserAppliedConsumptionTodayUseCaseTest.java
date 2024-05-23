package com.robinfood.app.usecases.hasuserappliedconsumptiontoday;

import com.robinfood.app.usecases.getorderdetailbyids.IGetOrderDetailByIdsUseCase;
import com.robinfood.app.usecases.getuserdatalistbyuserid.IGetUserDataListByUserIdAndDateUseCase;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.dtos.UserDataDTO;
import kotlin.collections.CollectionsKt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HasUserAppliedConsumptionTodayUseCaseTest {

    @Mock
    private IGetOrderDetailByIdsUseCase mockGetOrderDetailByIdsUseCase;

    @Mock
    private IGetUserDataListByUserIdAndDateUseCase mockGetUserDataListByUserIdAndDateUseCase;

    @InjectMocks
    private HasUserAppliedConsumptionTodayUseCase hasUserAppliedConsumptionTodayUseCase;

    private final LocalDate currentTime = LocalDate.of(2021, 6, 3);
    private final Long userId = 1L;

    private final List<UserDataDTO> userDataDTOS = new ArrayList<>(Arrays.asList(
            new UserDataDTO(
                    "michael@gmail.com",
                    "Michael",
                    null,
                    "Perez",
                    "300574846",
                    1L
            ),
            new UserDataDTO(
                    "cristiano@gmail.com",
                    "Cristiano",
                    null,
                    "Messi",
                    "300574846",
                    1L
            ),
            new UserDataDTO(
                    "Lionel@gmail.com",
                    "Lionel",
                    3L,
                    "Ronaldo",
                    "300574846",
                    1L
            )
    ));

    private final List<OrderDetailDTO> orderDetailWithConsumptionDTOS = new ArrayList<>(Arrays.asList(
            new OrderDetailDTO(
                    3900.0,
                    false,
                    1L,
                    "1234",
                    "Notes"
            ),
            new OrderDetailDTO(
                    3900.0,
                    false,
                    2L,
                    "5678",
                    "Notes"
            )
    ));

    private final List<OrderDetailDTO> orderDetailWithoutConsumptionDTOS = new ArrayList<>(Arrays.asList(
            new OrderDetailDTO(
                    3900.0,
                    false,
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

    @Test
    void test_When_User_Has_Not_Applied_Consumption_Discount_Today() {
        when(mockGetUserDataListByUserIdAndDateUseCase.invoke(currentTime, userId))
                .thenReturn(userDataDTOS);
        final List<Long> orderIds = CollectionsKt.map(
                userDataDTOS,
                UserDataDTO::getOrderId
        );
        when(mockGetOrderDetailByIdsUseCase.invoke(orderIds)).thenReturn(orderDetailWithConsumptionDTOS);

        final Boolean result = hasUserAppliedConsumptionTodayUseCase.invoke(currentTime, userId);

        assertEquals(false, result);
    }

    @Test
    void test_When_User_Has_Applied_Consumption_Discount_Today() {
        when(mockGetUserDataListByUserIdAndDateUseCase.invoke(currentTime, userId))
                .thenReturn(userDataDTOS);
        final List<Long> orderIds = CollectionsKt.map(
                userDataDTOS,
                UserDataDTO::getOrderId
        );
        when(mockGetOrderDetailByIdsUseCase.invoke(orderIds)).thenReturn(orderDetailWithoutConsumptionDTOS);

        final Boolean result = hasUserAppliedConsumptionTodayUseCase.invoke(currentTime, userId);

        assertEquals(true, result);
    }

}
