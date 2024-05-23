package com.robinfood.app.usecases.getuserorderdetail;

import static java.util.Objects.nonNull;

import com.robinfood.app.usecases.getservicedetail.IGetServiceDetailByUIdUseCase;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDetailDTO;
import com.robinfood.core.dtos.services.ServiceDetailDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.userorderdetail.IUserOrderDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of IGetUserOrderDetailByUIdUseCase
 */
@Service
@Slf4j
public class GetUserOrderDetailByUIdUseCase implements IGetUserOrderDetailByUIdUseCase {

    private final IGetServiceDetailByUIdUseCase getServiceDetailByUIdUseCase;
    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IUserOrderDetailRepository userOrderDetailRepository;

    public GetUserOrderDetailByUIdUseCase(IGetServiceDetailByUIdUseCase getServiceDetailByUIdUseCase,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IUserOrderDetailRepository userOrderDetailRepository) {
        this.getServiceDetailByUIdUseCase = getServiceDetailByUIdUseCase;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.userOrderDetailRepository = userOrderDetailRepository;
    }

    @Override
    public Result<ResponseOrderDetailDTO> invoke(
            String orderUId,
            Long userId
    ) {
        var token = getTokenBusinessCapabilityUseCase.invoke();

        log.info("Get orders detail by user repository");
        Result<ResponseOrderDetailDTO> result = userOrderDetailRepository.getOrderDetail(
            orderUId,
            token.getAccessToken(),
            userId
        );

        if (result instanceof Result.Success) {
            Result<ServiceDetailDTO> resultServiceDetail = getServiceDetailByUIdUseCase.invoke(orderUId);

            if (resultServiceDetail instanceof Result.Success) {
                ResponseOrderDetailDTO orderDetailDTO = ((Result.Success<ResponseOrderDetailDTO>) result).getData();
                ServiceDetailDTO serviceDetailDTO = ((Result.Success<ServiceDetailDTO>) resultServiceDetail).getData();

                if (nonNull(serviceDetailDTO.getDeliveryCourier())) {
                    return new Result.Success<>(
                        orderDetailDTO.toBuilder()
                            .deliveryCourier(serviceDetailDTO.getDeliveryCourier())
                            .build()
                    );
                }
            }
        }

        return result;
    }
}
