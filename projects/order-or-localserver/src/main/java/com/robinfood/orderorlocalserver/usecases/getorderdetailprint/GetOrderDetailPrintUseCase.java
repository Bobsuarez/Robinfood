package com.robinfood.orderorlocalserver.usecases.getorderdetailprint;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailDTO;
import com.robinfood.orderorlocalserver.entities.token.TokenModelEntity;
import com.robinfood.orderorlocalserver.enums.Result;
import com.robinfood.orderorlocalserver.repositories.orderdetailprint.IOrderDetailPrintRepository;
import com.robinfood.orderorlocalserver.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetOrderDetailPrintUseCase implements IGetOrderDetailPrintUseCase {

    private final IOrderDetailPrintRepository orderDetailPrintRepository;
    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    public GetOrderDetailPrintUseCase(
        IOrderDetailPrintRepository orderDetailPrintRepository,
        IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase
    ) {
        this.orderDetailPrintRepository = orderDetailPrintRepository;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;

    }

    @Override
    public Result<List<OrderDetailDTO>> invoke(
            List<Long> orderIds, List<String> orderUids, List<String> orderUuid
    ) {

        log.info("Starting use case to get the order detail");

        TokenModelEntity token = getTokenBusinessCapabilityUseCase.invoke();

        Result<List<OrderDetailDTO>> orderDetailListResult =  orderDetailPrintRepository.getOrderDetailPrint(
                token.getAccessToken(), orderIds, orderUids, orderUuid
        );

        log.info("Validating if order detail repository response is instanceof error. " +
                "Result is: {}", orderDetailListResult);

        if (orderDetailListResult instanceof Result.Error) {

            log.info("Order detail repository response is an error.");
            return orderDetailListResult;
        }

        return orderDetailListResult;
    }
}
