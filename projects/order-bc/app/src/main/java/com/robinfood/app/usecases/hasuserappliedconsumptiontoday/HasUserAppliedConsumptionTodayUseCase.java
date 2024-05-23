package com.robinfood.app.usecases.hasuserappliedconsumptiontoday;

import com.robinfood.app.usecases.getorderdetailbyids.IGetOrderDetailByIdsUseCase;
import com.robinfood.app.usecases.getuserdatalistbyuserid.IGetUserDataListByUserIdAndDateUseCase;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.dtos.UserDataDTO;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of IHasUserAppliedConsumptionTodayUseCase
 */
@Component
@Slf4j
public class HasUserAppliedConsumptionTodayUseCase implements IHasUserAppliedConsumptionTodayUseCase {

    private final IGetOrderDetailByIdsUseCase getOrderDetailByIdsUseCase;
    private final IGetUserDataListByUserIdAndDateUseCase getUserDataByUserIdAndDateUseCase;

    public HasUserAppliedConsumptionTodayUseCase(
            IGetOrderDetailByIdsUseCase getOrderDetailByIdsUseCase,
            IGetUserDataListByUserIdAndDateUseCase getUserDataByUserIdAndDateUseCase
    ) {
        this.getOrderDetailByIdsUseCase = getOrderDetailByIdsUseCase;
        this.getUserDataByUserIdAndDateUseCase = getUserDataByUserIdAndDateUseCase;
    }

    @Override
    public Boolean invoke(LocalDate createdAt, Long userId) {
        log.info("Starting process to user applied consumption today by user id [{}]", userId);

        final List<Long> orderIds = CollectionsKt.map(
                getUserDataByUserIdAndDateUseCase.invoke(createdAt, userId),
                UserDataDTO::getOrderId
        );
        return CollectionsKt.any(
                getOrderDetailByIdsUseCase.invoke(orderIds),
                OrderDetailDTO::getHasConsumption
        );
    }
}
