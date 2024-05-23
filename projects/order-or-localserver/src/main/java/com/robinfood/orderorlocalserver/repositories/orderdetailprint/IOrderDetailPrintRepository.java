package com.robinfood.orderorlocalserver.repositories.orderdetailprint;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailDTO;
import com.robinfood.orderorlocalserver.enums.Result;

import java.util.List;

public interface IOrderDetailPrintRepository {

    Result<List<OrderDetailDTO>> getOrderDetailPrint(
            String token,
            List<Long> orderIds,
            List<String> orderUids,
            List<String> orderUuid
    );
}
