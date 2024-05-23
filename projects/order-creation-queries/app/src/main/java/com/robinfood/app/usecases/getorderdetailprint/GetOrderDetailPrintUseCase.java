package com.robinfood.app.usecases.getorderdetailprint;

import static com.robinfood.app.usecases.getorderdetail.GetOrderDetailUseCase.invertReplacementPortions;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.orderdetailprint.IOrderDetailPrintRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of IGetOrderDetailPrintUseCase
 */
@Service
@Slf4j
public class GetOrderDetailPrintUseCase implements IGetOrderDetailPrintUseCase {

    private final IOrderDetailPrintRepository orderDetailPrintRepository;
    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    public GetOrderDetailPrintUseCase(IOrderDetailPrintRepository orderDetailPrintRepository,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase) {
        this.orderDetailPrintRepository = orderDetailPrintRepository;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
    }

    @Override
    public Result<List<OrderDetailDTO>> invoke(
            List<Long> orderIds,
            List<String> orderUids,
            List<String> orderUuid
    ) {

        var token = getTokenBusinessCapabilityUseCase.invoke();

        log.info("Get orders details repository");
        Result<List<OrderDetailDTO>> orderDetailListResult =  orderDetailPrintRepository.getOrderDetailPrint(
                token.getAccessToken(),
                orderIds,
                orderUids,
                orderUuid
        );

        log.info("Validate instanceof get orders details print use case ");
        if (orderDetailListResult instanceof Result.Success) {
            List<OrderDetailDTO> orderDetailDTOList = ((Result.Success<List<OrderDetailDTO>>) orderDetailListResult)
                    .getData();

            log.info("Get orders detail, data found {}", orderDetailDTOList);

            for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
                invertReplacementPortions(orderDetailDTO.getProducts());
            }
        }

        return orderDetailListResult;
    }
}
