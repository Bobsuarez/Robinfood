package com.robinfood.app.usecases.getorderdetailbyidsanduids;

import com.robinfood.app.usecases.getdetailorderbyuids.IGetOrderDetailByUidsUseCase;
import com.robinfood.app.usecases.getorderdetailbyuuids.IGetOrderDetailByUuidsUseCase;
import com.robinfood.app.usecases.getorderdetailorder.IGetOrderDetailOrderUseCase;
import com.robinfood.core.dtos.GetOrderDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of IGetOrderDetailByIdsAndsUidsUseCase
 */
@Service
@Slf4j
public class GetOrderDetailByIdsAndUids implements IGetOrderDetailByIdsAndsUidsUseCase {

    private final IGetOrderDetailByUidsUseCase getOrderDetailByUidsUseCase;
    private final IGetOrderDetailOrderUseCase getOrderDetailOrderUseCase;

    private final IGetOrderDetailByUuidsUseCase getOrderDetailByUuidsUseCase;

    public GetOrderDetailByIdsAndUids(
            IGetOrderDetailByUidsUseCase getOrderDetailByUidsUseCase,
            IGetOrderDetailOrderUseCase getOrderDetailOrderUseCase,
            IGetOrderDetailByUuidsUseCase getOrderDetailByUuidsUseCase
    ) {
        this.getOrderDetailByUidsUseCase = getOrderDetailByUidsUseCase;
        this.getOrderDetailOrderUseCase = getOrderDetailOrderUseCase;
        this.getOrderDetailByUuidsUseCase = getOrderDetailByUuidsUseCase;
    }

    @Override
    public List<GetOrderDetailDTO> invoke(List<Long> orderIds, List<String> orderUids, List<String> orderUuid) {

        log.info(
                "Starting process to get order detail by order ids: {} or order uids: {} or order uuid: {}",
                orderIds,
                orderUids,
                orderUuid
        );

        List<GetOrderDetailDTO> getOrderDetailDTOS = new ArrayList<>();

        if (!orderIds.isEmpty()) {
            log.info("Starting process to get order detail by order ids: {}", orderIds);
            getOrderDetailDTOS.addAll(getOrderDetailOrderUseCase.invoke(orderIds));
        }

        if (!orderUids.isEmpty()) {
            log.info("Starting process to get order detail by order uids: {}", orderUids);
            getOrderDetailDTOS.addAll(getOrderDetailByUidsUseCase.invoke(orderUids));
        }

        if(!orderUuid.isEmpty()) {
            log.info("Starting process to get order detail by order uuids: {}", orderUuid);
            getOrderDetailDTOS.addAll(this.getOrderDetailByUuidsUseCase.invoke(orderUuid));
        }

        return getOrderDetailDTOS;
    }
}
