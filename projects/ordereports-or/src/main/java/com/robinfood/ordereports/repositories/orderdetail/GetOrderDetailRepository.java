package com.robinfood.ordereports.repositories.orderdetail;

import com.robinfood.app.library.comunication.ClientRetroFit;
import com.robinfood.app.library.dto.Result;
import com.robinfood.app.library.enums.EMessageStandard;
import com.robinfood.app.library.exception.app.NetworkConnectionException;
import com.robinfood.ordereports.dtos.orders.OrderDetailDTO;
import com.robinfood.ordereports.entities.ApiResponseEntity;
import com.robinfood.ordereports.exception.OrderDetailBcException;
import com.robinfood.ordereports.network.api.OrderDetailBcAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.robinfood.ordereports.enums.OrderDetailLogEnum.ERROR_GET_ORDER_DETAIL_REQUEST;
import static com.robinfood.ordereports.enums.OrderDetailLogEnum.GETTING_ORDER_DETAIL_REQUEST;

@Slf4j
@Repository
public class GetOrderDetailRepository implements IGetOrderDetailRepository {

    private final OrderDetailBcAPI orderDetailBcAPI;

    public GetOrderDetailRepository(OrderDetailBcAPI orderDetailBcAPI) {
        this.orderDetailBcAPI = orderDetailBcAPI;
    }

    @Override
    public OrderDetailDTO getOrderDetail(String token, String transactionUuid) {
        try {
            OrderDetailDTO orderDetailDTO =
                    ((Result.Success<ApiResponseEntity<OrderDetailDTO>>)  ClientRetroFit.safeAPICall(
                    orderDetailBcAPI.getOrderDetail(transactionUuid, token)
            )).getData().getData();

            log.info(GETTING_ORDER_DETAIL_REQUEST.getMessage(), transactionUuid, orderDetailDTO.getId());

            return orderDetailDTO;
        } catch (NetworkConnectionException e) {
            log.error(ERROR_GET_ORDER_DETAIL_REQUEST.getMessage(), transactionUuid, e);
            throw new OrderDetailBcException(e, EMessageStandard.ERROR_INTERNAL_SERVER);
        }
    }
}
